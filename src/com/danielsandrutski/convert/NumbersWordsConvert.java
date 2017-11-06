package com.danielsandrutski.convert;

import com.danielsandrutski.convert.namedata.BigDigit;
import com.danielsandrutski.convert.namedata.NameData;
import com.danielsandrutski.convert.namedata.SmallDigit;

import java.text.DecimalFormat;

/**
 * Создано DanielSand 03.11.2017
 * Класс, позволяющий отобразить числа прописью
 * @version 1.0
 * @autor Daniel Sandrutski
 */
public class NumbersWordsConvert {
    /** Разделитель в выходной строке */
    private static final String SEPARATOR = " ";
    /** Шаблон для преобразования исходного числа в строку, с дальнейшим разделением на цедую часть и дробную */
    private static final DecimalFormat df = new DecimalFormat("#.################################################");

    /** Исходное число */
    private double baselineNumber;
    /** Результат преобразования */
    private String result;

    /** Наименования разрядов больших тысячи */
    private BigDigit[] bigDigits;
    /** Наименование разрядов и необходимых чисел меньших тысячи */
    private SmallDigit[] smallDigits;
    /** Наименования разрядов после запятой */
    private BigDigit[] fractionDigits;


    /**
     * Конструктор по умолчанию
     */
    public NumbersWordsConvert(){
        this.baselineNumber = 0;
        NameData nameData = new NameData();
        this.bigDigits = nameData.getBigDigits();
        this.smallDigits = nameData.getSmallDigit();
        this.fractionDigits = nameData.getFractionDigits();
    }

    /**
     * Конструктор, принимающий исходные данные в числовом формате
     * @param number Исходное число
     */
    public NumbersWordsConvert(double number){
        this.baselineNumber = number;
        NameData nameData = new NameData();
        this.bigDigits = nameData.getBigDigits();
        this.smallDigits = nameData.getSmallDigit();
        this.fractionDigits = nameData.getFractionDigits();
    }

    /**
     * Конструктор, принимающий исходные данные в строковом формате
     * @param number Исходное число в формате строки
     */
    public NumbersWordsConvert(String number) throws NumberFormatException {
        this.baselineNumber = Double.parseDouble(number);
        NameData nameData = new NameData();
        this.bigDigits = nameData.getBigDigits();
        this.smallDigits = nameData.getSmallDigit();
        this.fractionDigits = nameData.getFractionDigits();
    }

    /**
     * Устанавливающий метод исходного числа
     * @param baselineNumber Исходное число
     */
    public void setBaselineNumber(double baselineNumber) {
        this.baselineNumber = baselineNumber;
    }

    /**
     * Метод, инициализирующий преобразование вещественного числа в строку
     * @return Возвращает исходное число прописью
     */
    public String ConvertDouble(){
        if(baselineNumber == 0) return "ноль";

        //Проверяем на отрицательное число
        if(baselineNumber <= 0) {
            result = "минус" + SEPARATOR;
            baselineNumber *= -1;
        } else {
            result = "";
        }

        //Разбиваем исходное число на его целую и дробную часть
        double integerPart = Math.floor(baselineNumber);
        String[] stringParts = String.valueOf(df.format(baselineNumber)).split(","); //Разделяем число на целую и дробную части
        if((int)Math.ceil(stringParts[0].length() / 3.0) > bigDigits.length) {
            return "Слишком большое число!";
        }
        if(stringParts.length > 1) { //Если у числа имеется дробная часть
            int fractionDigit = stringParts[1].length(); //Определяем количество разрядов в дробной части
            if(fractionDigit > fractionDigits.length){
                return "Слишком много знаков в дробной части числа!";
            }
            //Преобразовываем целую часть
            if(integerPart == 0) {
                result = "ноль целых ";
            } else {
                result += InitConvert(integerPart, (int)Math.ceil(stringParts[0].length() / 3.0), "целая ", "целые ", "целых ", 1);
            }
            double fractionalPart = Double.parseDouble(stringParts[1]);
            if (fractionDigit > 0) {
                //Преобразовываем дробную часть
                result += InitConvert(fractionalPart, fractionDigit,
                        fractionDigits[fractionDigit - 1].getOne(),
                        fractionDigits[fractionDigit - 1].getTwoToFour(),
                        fractionDigits[fractionDigit - 1].getAnother(),
                        fractionDigits[fractionDigit - 1].getSex());
            }
        } else {
            result += InitConvert(integerPart, (int)Math.ceil(stringParts[0].length() / 3.0),"", "", "", 0);
        }
        return result.trim();
    }

    /**
     * Метод, инициализирующий преобразование целого числа в строку, с добавлением дополнительного наименования
     * @param one Дополнительная надпись в единственном числе
     * @param twoToFour Дополнительная надпись для чисел от 2 до 4
     * @param another Дополнительная надпись для остального ряда чисел
     * @param sex Род дополнительной надписи (0 - мужской, 1 - женский)
     * @return Исходное число прописью
     */
    public String ConvertIntegerPartWithLabel(String one, String twoToFour, String another, int sex){
        if(baselineNumber == 0) return "ноль " + another;

        //Проверяем на отрицательное число
        if(baselineNumber <= 0) {
            result = "минус" + SEPARATOR;
            baselineNumber *= -1;
        } else {
            result = "";
        }

        //Выделяем целую часть
        double integerPart = Math.floor(baselineNumber);
        if((int)Math.ceil(String.valueOf(integerPart).length() / 3.0) > bigDigits.length) {
            return "Слишком большое число!";
        }
        return (result + InitConvert(integerPart, -1, one, twoToFour, another, sex)).trim();
    }

    /**
     * Метод основной инициализации процесса конвертации
     * @param _baselineNumber Исходное число (целое)
     * @param _digit Количество степеней тысячи в числе
     * @param _one Дополнительная надпись в единственном числе
     * @param _twoToFour Дополнительная надпись для чисел от 2 до 4
     * @param _another Дополнительная надпись для остального ряда чисел
     * @param _sex Род дополнительной надписи (0 - мужской, 1 - женский)
     * @return Исходное число прописью, включая дополнительную надпись в конце
     */
    private String InitConvert(double _baselineNumber, int _digit, String _one, String _twoToFour, String _another, int _sex){
        double integerPart = Math.floor(_baselineNumber); //Определяем целую часть числа (с дробной не работаем, поэтому отметаем)
        //Заносим дополнительную надпись в массив, чтобы она добавилась в конце преобразования
        bigDigits[0].setSex(_sex);
        bigDigits[0].setOne(_one);
        bigDigits[0].setTwoToFour(_twoToFour);
        bigDigits[0].setAnother(_another);
        if(_digit >= 1) //Было ли принято количество степеней тысячи. Если нет вычисляем сами
            return Converting(integerPart, _digit);
        else
            return Converting(integerPart, (int)Math.ceil(String.valueOf(df.format(_baselineNumber)).split(",")[0].length() / 3.0));
    }

    /**
     * Основной метод конвертации
     * @param _baselineNumber Исходное число для перевода в строку
     * @param _digit Количество степеней тысячи в числе
     * @return Строка, содержащая число прописью
     */
    private String Converting (double _baselineNumber, int _digit)
    {
        StringBuilder bufString = new StringBuilder();

        //Выделить группу, лежащую в крайней слева степени тысячи
        double div = Math.pow(1000.0, _digit - 1);
        int bufNumber = (int)(_baselineNumber / div);

        if (bufNumber == 0) {
            //Если число содержит еще разряды, то значит в данной степени одни нули, и необходимо продолжить конвертацию ничего не добавляя
            if(_digit > 0)
                return Converting(_baselineNumber % div, --_digit);
            else
                return ""; //Иначе заканчиваем конвертацию
        } else {
            //Определение вхождения сотен в число
            if(bufNumber >= 100){
                bufString.append(smallDigits[bufNumber / 100].getHun());
                bufNumber %= 100;
            }
            //Определение вхождения десятков в число
            if(bufNumber >= 20) {
                bufString.append(smallDigits[bufNumber / 10].getDec());
                bufNumber%=10;
            }
            //Определение вхождения чисел от 10 до 19
            if(bufNumber >= 10) {
                bufString.append(smallDigits[bufNumber - 10].getTwo());
            } else if(bufNumber >= 1) { //Определение вхождения единиц в число
                bufString.append(smallDigits[bufNumber].getOne()[bigDigits[_digit - 1].getSex()]);
            }
            //Добавление наименования степеней тысячи
            switch(bufNumber){
                case 1:
                    bufString.append(bigDigits[_digit - 1].getOne());
                    break;
                case 2: case 3:
                case 4:
                    bufString.append(bigDigits[_digit - 1].getTwoToFour());
                    break;
                default:
                    bufString.append(bigDigits[_digit - 1].getAnother());
                    break;
            }
        }
        return bufString + Converting(_baselineNumber % div, --_digit); //Продолжаем конвертацию
    }
}
