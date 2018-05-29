package effectiveJavaStudy.c34_enum;

public enum Day {
    MONDAY(DayType.WEEKDAY), TUESDAY(DayType.WEEKDAY), WEDNESDAY(DayType.WEEKDAY), THURSDAY(DayType.WEEKDAY), FRIDAY(
            DayType.WEEKDAY), SATURDAY(DayType.WEEKDAY), SUNDAY(DayType.WEEKDAY);
    private final DayType dayType;

    Day(DayType daytype) {
        this.dayType = daytype;
    }

    void apply() {
        dayType.apply();
    }

    private enum DayType {
        WEEKDAY {
            @Override
            void apply() {
                System.out.println("hi, weekday");
            }
        },
        WEEKEND {
            @Override
            void apply() {
                System.out.println("hi, weekend");
            }
        };
        abstract void apply();
    }
    
    public static void main(String[] args) {
        Day day1 = Day.MONDAY;
        day1.apply();
    }
}
