package com.dingmei.dto;

/**
 * Created by ying on 15/10/27.
 */
public class MyTimeDTO implements Comparable<MyTimeDTO>{

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
            return String.format("%d.%d.%d",year,month,day);
        }else if("MM".equals(timeStyle)){
            return String.format("%d月份",month);
        }else if(("yyyy.MM").equals(timeStyle)){
            return String.format("%d.%d",year,month);
        }else if(("quarter").equals(timeStyle)){
            return String.format("%d年第%d季度",year,quarter);
        }else if(("yyyy.MM.week").equals(timeStyle)){
            return String.format("%d.%d.第%d周",year,month,week);
        }else{
            return "*";
        }
    }

    @Override
    public int compareTo(MyTimeDTO o) {
        if(this.getYear() != null && o.getYear() != null && this.getYear() > o.getYear()){
            return 1;
        }else if(this.getYear() != null && o.getYear() != null && this.getYear() < o.getYear()){
            return -1;
        }else{
            if(this.getMonth() != null && o.getMonth() != null && this.getMonth() > o.getMonth()){
                return 1;
            }else if(this.getMonth() != null && o.getMonth() != null && this.getMonth() < o.getMonth()){
                return -1;
            }else{
                if(this.getQuarter() != null && o.getQuarter() != null && this.getQuarter() > o.getQuarter()){
                    return 1;
                }else if(this.getQuarter() != null && o.getQuarter() != null && this.getQuarter() < o.getQuarter()){
                    return -1;
                }else{
                    if(this.getWeek() != null && o.getWeek() != null && this.getWeek() > o.getWeek()){
                        return 1;
                    }else if(this.getWeek() != null && o.getWeek() != null && this.getWeek() < o.getWeek()){
                        return -1;
                    }else{
                        if(this.getDay() != null && o.getDay() != null && this.getDay() > o.getDay()){
                            return 1;
                        }else if(this.getDay() != null && o.getDay() != null && this.getDay() < o.getDay()){
                            return -1;
                        }else{
                            return 0;
                        }
                    }
                }
            }
        }
    }

    public String[] getTimeKeys(){
        if(("yyyy.MM.dd").equals(timeStyle)){
            return new String[]{"year","month","day"};
        }else if("MM".equals(timeStyle)){
            return new String[]{"month"};
        }else if(("yyyy.MM").equals(timeStyle)){
            return new String[]{"year","month"};
        }else if(("quarter").equals(timeStyle)){
            return new String[]{"year","quarter"};
        }else if(("yyyy.MM.week").equals(timeStyle)){
            return new String[]{"year","month","week"};
        }else{
            return new String[]{};
        }
    }

    public Boolean isEmpty(){
        if((this.year == null || this.year == 0)
                &&(this.quarter == null || this.quarter == 0)
                &&(this.month == null || this.month == 0)
                &&(this.week == null || this.week == 0)
                &&(this.day == null || this.day == 0)){
            return true;
        }
        return false;
    }
}
