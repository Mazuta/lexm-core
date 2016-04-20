package ru.lexmin.lexm_core;

import ru.lexmin.lexm_core.dto.ReceivedText;
import ru.lexmin.lexm_core.dto.WordStat;

/**
 * Данный интерфейс описывает основной функционал анализа получаемого от
 * пользователя текста
 * 
 * @author Anton Yurchenko
 *
 */
public interface TextAnalyzer {

	/**
	 * Мемод получает объект класса {@link WordStat}, заполненный данными,
	 * актуальными для передаваемого объекта {@link ReceivedText}
	 * 
	 * @param receivedText
	 *            {@link ReceivedText}
	 * @return возврашает заполненный {@link WordStat}
	 */
	public abstract WordStat getWordStat(ReceivedText receivedText);

}
