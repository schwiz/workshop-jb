package ii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    //Task 11
    //Make class MyDate implement Comparable.
    override fun compareTo(other: MyDate): Int {
        if(year != other.year) return year.compareTo(other.year)
        if(month != other.month) return month.compareTo(other.month)
        return dayOfMonth.compareTo(other.dayOfMonth)
    }

    //Task 13
    //To make '..' work add 'MyDate.rangeTo()' extension function returning DateRange.
    //Add all changes to the file MyDate.kt.
    fun rangeTo(end : MyDate) = DateRange(this, end)
}

//Task 15
//(1). Add an extension function 'plus()' to MyDate, taking TimeInterval as an argument.
//(2). Support adding several time intervals to a date. Add an extra class.
class RepeatedTimeInterval (val timeInterval: TimeInterval, val number: Int)
fun MyDate.plus(interval : TimeInterval)= addTimeIntervals(interval, 1);
fun MyDate.plus(intervals : RepeatedTimeInterval) = addTimeIntervals(intervals.timeInterval, intervals.number)
fun TimeInterval.times(int : Int) = RepeatedTimeInterval(this, int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}


class DateRange(public override val start: MyDate, public override val end: MyDate) : Iterable<MyDate>, Range<MyDate> {

    //        Task 12
    //        Make class DateRange implement Iterable<MyDate>.
    //        Use object expression to implement Iterator<MyDate>.
    //        Use an utility function 'MyDate.nextDay()'.
    override fun iterator() = object : Iterator<MyDate> {

        var current : MyDate = start

        override fun next() : MyDate {
            val result = current
            current = current.nextDay()
            return result
        }

        override fun hasNext() : Boolean = current <= end
    }


    //    Task 14.
    //    Uncomment the commented line.
    //    For now being in interval is checked with the function 'fun <T> Iterable<T>.contains(T): Boolean'
    //    which is not optimal (you can check the boundaries instead of iterating over the whole range).
    //    Make class DateRange implement kotlin.Range<MyDate>.
    override fun contains(item: MyDate): Boolean {
        return item >= start && item <= end
    }
}
