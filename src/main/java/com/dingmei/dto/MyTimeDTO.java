package com.dingmei.dto;

/**
 * Created by ying on 15/10/27.
 */
public class MyTimeDTO {
    private String timeStyle;
    private Integer year;
    private Integer month;
    private Integer quarter;
    private Integer week;
    private Integer day;

    public MyTimeDTO(){

    }

    public MyTimeDTO(String timeStyle,Integer year,Integer month,Integer quarter,Integer week,Integer day){
        this.timeStyle = timeStyle;
        this.year = year;
        this.month = month;
        this.quarter = quarter;
        this.week = week;
        this.day = day;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getTimeStyle() {
        return timeStyle;
    }

    public void setTimeStyle(String timeStyle) {
        this.timeStyle = timeStyle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyTimeDTO myTimeDTO = (MyTimeDTO) o;

        if (day != null ? !day.equals(myTimeDTO.day) : myTimeDTO.day != null) return false;
        if (month != null ? !month.equals(myTimeDTO.month) : myTimeDTO.month != null) return false;
        if (quarter != null ? !quarter.equals(myTimeDTO.quarter) : myTimeDTO.quarter != null) return false;
        if (timeStyle != null ? !timeStyle.equals(myTimeDTO.timeStyle) : myTimeDTO.timeStyle != null) return false;
        if (week != null ? !week.equals(myTimeDTO.week) : myTimeDTO.week != null) return false;
        if (year != null ? !year.equals(myTimeDTO.year) : myTimeDTO.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeStyle != null ? timeStyle.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (quarter != null ? quarter.hashCode() : 0);
        result = 31 * result + (week != null ? week.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        return result;
    }

    /**
     * 解析日期格式
     * @return
     */
    @Override
    public String toString(){
        if(("yyyy.MM.dd").equals(timeStyle)){
            return year+"."+month+"."+day;
        }else if("MM".equals(timeStyle)){
            return month+"月份";
        }else if(("yyyy.MM").equals(timeStyle)){
            return year+"."+month;
        }else if(("quarter").equals(timeStyle)){
            return year+"第"+quarter+"季度";
        }else if(("yyyy.MM.week").equals(timeStyle)){
            return year+"."+month+".第"+week+"周";
        }else{
            return "*";
        }
    }
}
