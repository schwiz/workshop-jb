package ii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int {
        if(year != other.year) return year.compareTo(other.year)
        if(month != other.month) return month.compareTo(other.month)
        return dayOfMonth.compareTo(other.dayOfMonth)
    }
}

enum class TimeInterval {
    DAY
    WEEK
    YEAR
}

//        Make class DateRange implement Iterable<MyDate>.
//        Use object expression to implement Iterator<MyDate>.
//        Use an utility function 'MyDate.nextDay()'.

class DateRange(public val start: MyDate, public val end: MyDate) : Iterable<MyDate> {

    override fun iterator() = object : Iterator<MyDate> {

        var current : MyDate = start

        override fun next() : MyDate {
            val result = current
            current = current.nextDay()
            return result
        }

        override fun hasNext() : Boolean = current <= end
    }

}
