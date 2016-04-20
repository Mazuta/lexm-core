package ru.lexmin.lexm_core.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для передачи рзультов обработки текста в виде: - количество слов в
 * тексте - честота употребления каждого слова.
 * 
 * Количество слов хранится в поле countOfWords (int) Частота употребления
 * хранится в поле frequencyWords (Map<String, Integer>): - ключом является
 * слово - значением частора употребления в тексте
 * 
 * Поле receivedText - содержет ссылку на dto с текстом и процентом понимания.
 * 
 * @author Anton Yurchenko
 *
 */
public class WordStat extends Dto {

	/**
	 * Версия
	 */
	private static final long serialVersionUID = -1211530860332682161L;

	// ссылка на dto с исходным текстом и параметрами
	private ReceivedText receivedText;

	// кол-во слов в тексте, на который ссылка receivedText
	private int countOfWords;

	// статистика по часторе слов текста, на который ссылка receivedText,
	// отфильтрованная с учётом процента понимания
	private Map<String, Integer> frequencyWords;

	/**
	 * Констркутор по умолчанию
	 */
	public WordStat() {
		super();
	}

	/**
	 * Конструктор с параметрами
	 * 
	 * @param receivedText
	 * @param countOfWords
	 * @param frequencyWords
	 */
	public WordStat(ReceivedText receivedText, int countOfWords, Map<String, Integer> frequencyWords) {
		this.receivedText = receivedText;
		this.countOfWords = countOfWords;
		this.frequencyWords = frequencyWords;
	}

	/**
	 * Конструктор задаёт значение поля receivedText из передоваемого объекта.
	 * остальнве поля интциализируются значениями по умолчанию
	 * 
	 * @param receivedText
	 */
	public WordStat(ReceivedText receivedText) {
		this.receivedText = receivedText;
		// инициализация остальных полей значениями по умолчинию
		this.countOfWords = 0;
		this.frequencyWords = new HashMap<String, Integer>();
	}

	/**
	 * @return receivedText {@link ReceivedText}
	 */
	public ReceivedText getReceivedText() {
		return receivedText;
	}

	/**
	 * Устанавливает параметр
	 * 
	 * @param receivedText
	 *            receivedText {@link ReceivedText}
	 */
	public void setReceivedText(ReceivedText receivedText) {
		this.receivedText = receivedText;
	}

	/**
	 * @return countOfWords {@link int}
	 */
	public int getCountOfWords() {
		return countOfWords;
	}

	/**
	 * Устанавливает параметр
	 * 
	 * @param countOfWords
	 *            countOfWords {@link int}
	 */
	public void setCountOfWords(int countOfWords) {
		this.countOfWords = countOfWords;
	}

	/**
	 * @return frequencyWords {@link Map<String,Integer>}
	 */
	public Map<String, Integer> getFrequencyWords() {
		return frequencyWords;
	}

	/**
	 * Устанавливает параметр
	 * 
	 * @param frequencyWords
	 *            frequencyWords {@link Map<String,Integer>}
	 */
	public void setFrequencyWords(Map<String, Integer> frequencyWords) {
		this.frequencyWords = frequencyWords;
	}

}
