package com.danielsandrutski.convert.namedata;

/**
 * Создано DanielSand 03.11.2017
 * Класс, представляющий структуру наименования разрядов и чисел меньших тысячи
 * @version 1.0
 * @autor Daniel Sandrutski
 */
public class SmallDigit {
    /** Наименование числа от 1 до 10 */
    String[] one;
    /** Наименование числа от 10 до 20 */
    String two;
    /** Наименование числа от 20 до 100 */
    String dec;
    /** Наименование числа от 100 до 1000 */
    String hun;

    /**
     * Конструктор класса по умолчанию
     */
    public SmallDigit() { }

    /**
     * Конструктор класса, инициализирующий все поля принимаемыми значениями
     * @param one Наименование числа от 1 до 10
     * @param two Наименование числа от 10 до 20
     * @param dec Наименование числа от 20 до 100
     * @param hun Наименование числа от 100 до 1000
     */
    SmallDigit(String[] one, String two, String dec, String hun) {
        this.one = one;
        this.two = two;
        this.dec = dec;
        this.hun = hun;
    }

    /** Метод, позволяющий получить нименование числа от 1 до 10 */
    public String[] getOne() {
        return one;
    }

    /** Метод, позволяющий получить нименование числа от 10 до 20 */
    public String getTwo() {
        return two;
    }

    /** Метод, позволяющий получить нименование числа от 20 до 100 */
    public String getDec() {
        return dec;
    }

    /** Метод, позволяющий получить нименование числа от 100 до 1000 */
    public String getHun() {
        return hun;
    }

    /** Метод, позволяющий задать нименование числа от 1 до 10 */
    public void setOne(String[] one) {
        this.one = one;
    }

    /** Метод, позволяющий задать нименование числа от 10 до 20 */
    public void setTwo(String two) {
        this.two = two;
    }

    /** Метод, позволяющий задать нименование числа от 20 до 100 */
    public void setDec(String dec) {
        this.dec = dec;
    }

    /** Метод, позволяющий задать нименование числа от 100 до 1000 */
    public void setHun(String hun) {
        this.hun = hun;
    }
}