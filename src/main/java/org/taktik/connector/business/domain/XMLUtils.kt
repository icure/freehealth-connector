package org.taktik.connector.business.domain

import java.util.GregorianCalendar
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

fun newXMLGregorianCalendar(gc: GregorianCalendar): XMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)

fun newXMLGregorianCalendar(dateString: String): XMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateString)

fun newXMLGregorianCalendar(): XMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar())

fun newXMLGregorianCalendar(
    year: Int,
    month: Int,
    day: Int,
    hours: Int,
    minutes: Int,
    seconds: Int,
    milliseconds: Int,
    timezone: Int
): XMLGregorianCalendar {
    return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(year, month, day, timezone).apply {
        this.hour = hours
        this.minute = minutes
        this.second = seconds
        this.millisecond = milliseconds
    }
}

fun makeXMLGregorianCalendarFromHHMMSSLong(date: Long): XMLGregorianCalendar? {
    return newXMLGregorianCalendar().apply {
        hour = (date / 10000 % 100).toInt()
        minute = (date / 100 % 100).toInt()
        second = (date % 100).toInt()
    }
}
