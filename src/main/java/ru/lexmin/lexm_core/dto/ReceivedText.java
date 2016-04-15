package ru.lexmin.lexm_core.dto;

/**
 * Класс для получения от пользователя введённой информации а виде текста (text)
 * и процента понимания (percent).
 * 
 * @author Anton Yurchenko
 *
 */
public class ReceivedText extends Dto {

	/**
	 * Версия
	 */
	private static final long serialVersionUID = 5716001583591230233L;

	// текст, который ввёл пользователь
	private String text;

	// желаемый процент понимания текста пользователем
	private int percent;

	/**
	 * Пустой конструктор
	 */
	public ReceivedText() {
		super();
	}

	/**
	 * Конструктор с параметрами
	 * 
	 * @param text
	 *            {@link String}
	 * @param percent
	 *            int
	 */
	public ReceivedText(String text, int percent) {
		super();
		this.text = text;
		this.percent = percent;
	}

	/**
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return percent
	 */
	public int getPercent() {
		return percent;
	}

	/**
	 * @param percent
	 */
	public void setPercent(int percent) {
		this.percent = percent;
	}

}
