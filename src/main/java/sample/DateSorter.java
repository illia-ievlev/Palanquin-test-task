package sample;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * <p>
 * package sample;
 * <p>
 * import java.time.LocalDate;
 * import java.util.Collection;
 * import java.util.List;
 * <p>
 * /**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * Implement in single class. Don't chane constructor and signature method.
 */
public class DateSorter {

    public static void main(String[] args) {
        DateSorter dateSorter = new DateSorter();
        List<LocalDate> localDates = new ArrayList<>();
        localDates.add(LocalDate.of(2004, 07, 01));
        localDates.add(LocalDate.of(2005, 01, 02));
        localDates.add(LocalDate.of(2007, 01, 01));
        localDates.add(null);
        localDates.add(LocalDate.of(2032, 05, 03));
        System.out.println(dateSorter.sortDates2(localDates));
    }

    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
     * would sort to
     * (2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */

    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        if (unsortedDates == null) {
            throw new IllegalArgumentException("unsortedDates can't be null");
        }
        List<LocalDate> datesWithR = unsortedDates.stream()
                .filter(Objects::nonNull)
                .filter(date -> date.getMonth().toString().toLowerCase().contains("r"))
                .sorted()
                .collect(Collectors.toList());

        List<LocalDate> datesWithoutR = unsortedDates.stream()
                .filter(Objects::nonNull)
                .filter(date -> !date.getMonth().toString().toLowerCase().contains("r"))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        datesWithR.addAll(datesWithoutR);
        return datesWithR;
    }

    public Collection<LocalDate> sortDates2(List<LocalDate> unsortedDates) {
        if (unsortedDates == null) {
            throw new IllegalArgumentException("unsortedDates can't be null");
        }
        return unsortedDates.stream()
                .filter(Objects::nonNull)
                .sorted((date1, date2) -> {
                    boolean isMonthR1 = date1.getMonth().toString().toLowerCase().contains("r");
                    boolean isMonthR2 = date2.getMonth().toString().toLowerCase().contains("r");
                    if (isMonthR1 && isMonthR2) {
                        return date1.compareTo(date2);
                    } else if (isMonthR1) {
                        return -1; // Place date with month "R" higher
                    } else if (isMonthR2) {
                        return 1; // Place date without month "R" higher
                    } else {
                        return date2.compareTo(date1); // Reverse sorting for dates without "R"
                    }
                })
                .collect(Collectors.toList());
    }
}