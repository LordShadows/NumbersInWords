package com.danielsandrutski.convert.namedata;

/**
 * Создано DanielSand 03.11.2017
 * Класс, представляющий структуру наименования разрядов больших или равных тысячи
 * @version 1.0
 * @autor Daniel Sandrutski
 */
public class BigDigit {
    /** Род наименования (false - мужской, true - женский) */
    int sex;
    /** Наименование для единственного числа */
    String one;
    /** Наименование для чисел от 2 до 4 */
    String twoToFour;
    /** Наименование для чисел больших 4 */
    String another;

    /**
     * Конструктор класса по умолчанию
     */
    public BigDigit() { }

    /**
     * Конструктор класса, инициализирующий все поля принимаемыми значениями
     * @param sex Род наименования (false - мужской, true - женский)
     * @param one Наименование для единственного числа
     * @param twoToFour Наименование для чисел от 2 до 4
     * @param another Наименование для чисел больших 4
     */
    BigDigit(int sex, String one, String twoToFour, String another) {
        this.sex = sex;
        this.one = one;
        this.twoToFour = twoToFour;
        this.another = another;
    }

    /** Метод, позволяющий получить род наименования (false - мужской, true - женский)*/
    public int getSex() {
        return sex;
    }

    /** Метод, позволяющий получить наименование для единственного числа */
    public String getOne() {
        return one;
    }

    /** Метод, позволяющий получить наименование для чисел от 2 до 4 */
    public String getTwoToFour() {
        return twoToFour;
    }

    /** Метод, позволяющий получить наименование для чисел больших 4 */
    public String getAnother() {
        return another;
    }

    /** Метод, позволяющий задать род наименования (false - мужской, true - женский)*/
    public void setSex(int sex) {
        this.sex = sex;
    }

    /** Метод, позволяющий задать наименование для единственного числа */
    public void setOne(String one) {
        this.one = one;
    }

    /** Метод, позволяющий задать наименование для чисел от 2 до 4 */
    public void setTwoToFour(String twoToFour) {
        this.twoToFour = twoToFour;
    }

    /** Метод, позволяющий задать наименование для чисел больших 4 */
    public void setAnother(String another) {
        this.another = another;
    }
}
