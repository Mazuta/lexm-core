package ru.lexmin.lexm_core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import ru.lexmin.lexm_core.dto.ReceivedText;
import ru.lexmin.lexm_core.dto.WordStat;

/**
 * Этот класс является реализацией интерфейса TextAnalyzer
 * 
 * @author Anton Yurchenko
 *
 */
public class TextAnalyzerImp implements TextAnalyzer {

	/* Константы */
	private final int PERCENT_100 = 100;
	private final int ONE_WORD = 1;
	private final String SPACE = " ";

	// регулярное выражение: все испольуемые апострофы
	private final String ANY_APOSTROPHE = "[’]";

	// применяемый, стандартный апостроф
	private final String AVAILABLE_APOSTROPHE = "'";

	// регулярное выражение: не маленькие латинские буквы, не пробел и не
	// апостроф(')
	private final String ONLY_LATIN_CHARACTERS = "[^a-z\\s']";

	// регулярное выражение: пробелы, более двух подрят
	private final String SPACES_MORE_ONE = "\\s{2,}";

	/**
	 * Метод преобразует передаваемый текст в нижнеме регистру, производит
	 * фильтрацию текста. В тексте отсаются только латинские буквы, пробельные
	 * символы и верхний апостроф. Пробелы два и более подрят заменяются одним.
	 * 
	 * @param text
	 *            {@link String}
	 * @return отфильтрованный текст
	 */
	private String filterText(String text) {

		String resultText = text.toLowerCase().replaceAll(ANY_APOSTROPHE, AVAILABLE_APOSTROPHE)
				.replaceAll(ONLY_LATIN_CHARACTERS, SPACE).replaceAll(SPACES_MORE_ONE, SPACE);

		return resultText;
	}

	/**
	 * Метод преобразует получаемый текст в Map<{слво}, {количество}>
	 * 
	 * @param text
	 *            {@link String}
	 * @return заполненный Map
	 */
	private Map<String, Integer> getWordsMap(String text) {

		Map<String, Integer> wordsMap = new HashMap<String, Integer>();

		String newWord = "";

		Pattern patternWord = Pattern.compile("(?<word>[a-z']+)");
		Matcher matcherWord = patternWord.matcher(text);

		while (matcherWord.find()) {

			newWord = matcherWord.group("word");

			if (wordsMap.containsKey(newWord)) {

				wordsMap.replace(newWord, wordsMap.get(newWord) + ONE_WORD);

			} else {

				wordsMap.put(newWord, ONE_WORD);

			}
		}

		return wordsMap;
	}

	/**
	 * Метод возвращает общее количество слов, суммируя частоту употребления
	 * слов в получаемом Map
	 * 
	 * @param wordsMap
	 *            {@link Map}
	 * @return общее количество слов в тексте, по которому составлен Map
	 */
	private int getCountOfWords(Map<String, Integer> wordsMap) {

		int countOfWords = 0;

		for (Integer value : wordsMap.values())
			countOfWords += value;

		return countOfWords;
	}

	/**
	 * Метод производит вычисление процентрого соотнашения аргумента
	 * numberXPercents от аргумента number100Percents
	 * 
	 * @param number100Percents
	 *            int
	 * @param numberXPercents
	 *            int
	 * @return прочентное соотношение
	 */
	private int getPercent(int number100Percents, int numberXPercents) {

		return (numberXPercents * PERCENT_100) / number100Percents;
	}

	/**
	 * Метод выполняет фильтрацию слов в массива, чтобы их количество покрывало
	 * заданный процент понимания текста
	 * 
	 * @param wordsMap
	 *            {@link Map}
	 * @param countOfWords
	 *            int
	 * @param percent
	 *            int
	 * @return возвращает отфильтрованный массив, элементы когорого
	 *         отсорвированы по убывающей
	 */
	private Map<String, Integer> filterWordsMap(Map<String, Integer> wordsMap, int countOfWords, int percent) {

		Map<String, Integer> resultMap = new LinkedHashMap<String, Integer>();

		int sumPercentOfWords = 0;

		Stream<Entry<String, Integer>> streamWords = wordsMap.entrySet().stream().sorted(Map.Entry.comparingByValue(
				(Integer value1, Integer value2) -> (value1.equals(value2)) ? 0 : ((value1 < value2) ? 1 : -1)));

		Iterator<Entry<String, Integer>> iterator = streamWords.iterator();

		while (iterator.hasNext() && (sumPercentOfWords < percent)) {

			Entry<String, Integer> wordEntry = iterator.next();

			resultMap.put(wordEntry.getKey(), wordEntry.getValue());

			sumPercentOfWords += getPercent(countOfWords, wordEntry.getValue());

		}

		return resultMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.lexmin.lexm_core.TextAnalyzer#getWordStat(ru.lexmin.lexm_core.dto.
	 * ReceivedText)
	 */
	@Override
	public WordStat getWordStat(ReceivedText receivedText) {

		WordStat wordStat = new WordStat(receivedText);

		Map<String, Integer> wordsMap = getWordsMap(filterText(receivedText.getText()));

		wordStat.setCountOfWords(getCountOfWords(wordsMap));

		wordStat.setFrequencyWords(filterWordsMap(wordsMap, wordStat.getCountOfWords(), receivedText.getPercent()));

		return wordStat;
	}

}
