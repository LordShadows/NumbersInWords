package com.danielsandrutski.convert.namedata;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.ArrayList;

/**
 * Создано DanielSand 03.11.2017
 * Класс предназначен для загрузки и хранения данных наименования
 * @version 1.0
 * @autor Daniel Sandrutski
 */
public class NameData {

    /** Наименования разрядов больших тысячи */
    private BigDigit[] bigDigits;

    /** Наименования разрядов после запятой */
    private BigDigit[] fractionDigits;

    /** Наименования разрядов и необходимых чисел меньших тысячи */
    private SmallDigit[] smallDigit;

    /**
     * Конструктор класса по умолчанию. Извлекает все необходимые данные из файлов
     */
    public NameData() {
        try {
            //Извлекаем массив наименования разрядов больших тысячи
            final XMLDecoder decoderBigDigit = new XMLDecoder(new BufferedInputStream(new FileInputStream("src/com/danielsandrutski/convert/namedata/src/_big_digits.xml")));
            bigDigits = convertToBigDigit((ArrayList<BigDigit>)decoderBigDigit.readObject());
            decoderBigDigit.close();
            //Извлекаем массив наименования разрядов после запятой
            final XMLDecoder decoderFractionDigit = new XMLDecoder(new BufferedInputStream(new FileInputStream("src/com/danielsandrutski/convert/namedata/src/_fraction_digits.xml")));
            fractionDigits = convertToBigDigit((ArrayList<BigDigit>)decoderFractionDigit.readObject());
            decoderBigDigit.close();
            //Извлекаем массив наименования разрядов и необходимых чисел меньших тысячи
            final XMLDecoder decoderSmallDigit= new XMLDecoder(new BufferedInputStream(new FileInputStream("src/com/danielsandrutski/convert/namedata/src/_small_digits.xml")));
            smallDigit = convertToSmallDigit((ArrayList<SmallDigit>)decoderSmallDigit.readObject());
            decoderBigDigit.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /** Метод, позволяющий получить наименования разрядов больших тысячи */
    public BigDigit[] getBigDigits() {
        return bigDigits;
    }

    /** Метод, позволяющий получить наименования разрядов после запятой */
    public BigDigit[] getFractionDigits() {
        return fractionDigits;
    }

    /** Метод, позволяющий получить наименования разрядов и необходимых чисел меньших тысячи */
    public SmallDigit[] getSmallDigit() {
        return smallDigit;
    }

    /**
     * Метод для конвертации ArrayList в Array
     * @param list Принимает ArrayList содержащий объекты SmallDigit
     * @return Возвращает соответсвующий массив
     */
    private SmallDigit[] convertToSmallDigit(final ArrayList<SmallDigit> list) {
        SmallDigit[] stockArr = new SmallDigit[list.size()];
        return list.toArray(stockArr);
    }

    /**
     * Метод для конвертации ArrayList в Array
     * @param list Принимает ArrayList содержащий объекты BigDigit
     * @return Возвращает соответсвующий массив
     */
    private BigDigit[] convertToBigDigit(final ArrayList<BigDigit> list) {
        BigDigit[] stockArr = new BigDigit[list.size()];
        return list.toArray(stockArr);
    }
}

