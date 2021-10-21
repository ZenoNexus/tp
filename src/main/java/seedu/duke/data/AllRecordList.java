package seedu.duke.data;

import seedu.duke.data.records.Budget;
import seedu.duke.data.records.Expenditure;
import seedu.duke.textfiletools.WriteToTextFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Stores 12 instances of (Integer month, RecordList monthRecordList) inside a Hashtable,
 * where month ranges from 1 to 12.
 */
public class AllRecordList {
    public final Hashtable<Integer, RecordList> allRecordList;
    public static String storageDirectory;

    /**
     * Constructor that creates 12 RecordLists upon construction.
     */
    public AllRecordList() {
        allRecordList = new Hashtable<>();
        for (int i = 1; i <= 12; i++) {
            allRecordList.put(i, new RecordList(i));
        }
    }

    private void saveToStorage(String storageDirectory) {
        WriteToTextFile textFileWriter = new WriteToTextFile();
        textFileWriter.reloadArrayToStorage(allRecordList, storageDirectory);
    }

    public void addBudget(double spendingLimit, int month, boolean isLoadingStorage) {
        allRecordList.get(month).addBudget(spendingLimit, month, isLoadingStorage);
        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }
    }

    /**
     * Adds an expenditure record into the RecordList.
     *
     * @param description      description of the expenditure
     * @param amount           amount spent
     * @param isLoadingStorage indicate if this command is called during loading or runtime
     */
    public void addExpenditure(String description, double amount, LocalDate date, boolean isLoadingStorage) {
        int month = date.getMonthValue();
        allRecordList.get(month).addExpenditure(description, amount, date, isLoadingStorage);
        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }
    }

    public void addLoan(String name, double amount, LocalDate date, boolean isLoadingStorage) {
        int month = date.getMonthValue();
        allRecordList.get(month).addLoan(name, amount, date, isLoadingStorage);
        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }
    }

    public void clearAll() {
        allRecordList.clear();
        for (int i = 1; i <= 12; i++) {
            allRecordList.put(i, new RecordList(i));
        }
    }

    public void deleteBudget(int month) {
        allRecordList.get(month).deleteBudget();
        saveToStorage(storageDirectory);
    }

    public void deleteExpenditure(int index, int month) {
        allRecordList.get(month).deleteExpenditure(index);
        saveToStorage(storageDirectory);
    }

    public ArrayList<Expenditure> getExpenditureRecords(int month) {
        return allRecordList.get(month).getExpenditureRecords();
    }

    public Budget getBudget(int month) {
        return allRecordList.get(month).getBudget();
    }

    public int getExpenditureListSize(int month) {
        return allRecordList.get(month).getExpenditureListSize();
    }

    public Expenditure getExpenditure(int index, int month) {
        return allRecordList.get(month).getExpenditure(index);
    }

    public boolean checkOverspending(int month) {
        return allRecordList.get(month).checkOverspending();
    }

    public int getMonthListSize(int month) {
        return allRecordList.get(month).getSize();
    }



    /*public int getAllSize() {
        int size = 0;
        for (RecordList monthList : allRecordList) {
            size += monthList.getSize();
        }
        return size;
    }*/
}
