package ii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int {
        if(year != other.year) return year.compareTo(other.year)
        if(month != other.month) return month.compareTo(other.month)
        return dayOfMonth.compareTo(other.dayOfMonth)
    }

    //To make '..' work add 'MyDate.rangeTo()' extension function returning DateRange.
    //Add all changes to the file MyDate.kt.
    fun rangeTo(end : MyDate) = DateRange(this, end)
}

enum class TimeInterval {
    DAY
    WEEK
    YEAR
}

//        Make class DateRange implement Iterable<MyDate>.
//        Use object expression to implement Iterator<MyDate>.
//        Use an utility function 'MyDate.nextDay()'.


class DateRange(public override val start: MyDate, public override val end: MyDate) : Iterable<MyDate>, Range<MyDate> {

    override fun iterator() = object : Iterator<MyDate> {

        var current : MyDate = start

        override fun next() : MyDate {
            val result = current
            current = current.nextDay()
            return result
        }

        override fun hasNext() : Boolean = current <= end
    }

//    Uncomment the commented line.
//    For now being in interval is checked with the function 'fun <T> Iterable<T>.contains(T): Boolean'
//    which is not optimal (you can check the boundaries instead of iterating over the whole range).
//    Make class DateRange implement kotlin.Range<MyDate>.
    override fun contains(item: MyDate): Boolean {
        return item >= start && item <= end
    }
}
