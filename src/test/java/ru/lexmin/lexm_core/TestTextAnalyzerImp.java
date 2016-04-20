package ru.lexmin.lexm_core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Этот класс проводит тестирование функционала TextAnalyzerImp.
 * 
 * @author Anton Yurchenko
 *
 */
public class TestTextAnalyzerImp {

	// исходный текст (общее количество слов в тексте 100)
	private final String TEXT_FOR_TEST = "Welcome to the GWT Developer’s Guide.\n\r"
			+ "This guide introduces the key concepts, tools, and libraries\n\r"
			+ "you’ll encounter when building web applications with GWT.\n\r"
			+ "The topics in this guide span project organization, coding,\n\r"
			+ "debugging, testing, optimizing,and publishing your web application.\n\r"
			+ "Note - If you’re new to GWT and eager to start playing immediately,\n\r"
			+ "you might want to try the Quick Start. For specific technical details,\n\r"
			+ "see the Reference guide and Articles. GWT projects can be organized\n\r"
			+ "in a variety of ways. However, particular conventions are encouraged to\n\r"
			+ "make it easy to identify which code is intended to run on the client.";

	// эталонный текст
	private final String ETALON_TEXT = "welcome to the gwt developer's guide "
			+ "this guide introduces the key concepts tools and libraries "
			+ "you'll encounter when building web applications with gwt "
			+ "the topics in this guide span project organization coding "
			+ "debugging testing optimizing and publishing your web application "
			+ "note if you're new to gwt and eager to start playing immediately "
			+ "you might want to try the quick start for specific technical details "
			+ "see the reference guide and articles gwt projects can be organized "
			+ "in a variety of ways however particular conventions are encouraged to "
			+ "make it easy to identify which code is intended to run on the client ";

	// эталонный массив слов
	private final Map<String, Integer> ETALON_WORDS_MAP = new HashMap<String, Integer>();

	// эталонный отфильтрованный массив под 33% понимания
	private final Map<String, Integer> ETALON_FILTRED_WORDS_MAP = new LinkedHashMap<String, Integer>();

	// суммарное количество слов в эталонном Map
	private final int ETALON_COUNT_OF_WORDS = 100;

	// эталонное количество процентов
	private final int ETALON_PERCENT = 33;

	/**
	 * Конструктор супер класса В этом конструкторе заполняется эталонный массив
	 * слов значениями.
	 */
	public TestTextAnalyzerImp() {
		super();

		this.ETALON_FILTRED_WORDS_MAP.put("to", 7);
		this.ETALON_FILTRED_WORDS_MAP.put("the", 6);
		this.ETALON_FILTRED_WORDS_MAP.put("gwt", 4);
		this.ETALON_FILTRED_WORDS_MAP.put("guide", 4);
		this.ETALON_FILTRED_WORDS_MAP.put("and", 4);
		this.ETALON_FILTRED_WORDS_MAP.put("this", 2);
		this.ETALON_FILTRED_WORDS_MAP.put("start", 2);
		this.ETALON_FILTRED_WORDS_MAP.put("in", 2);
		this.ETALON_FILTRED_WORDS_MAP.put("web", 2);

		this.ETALON_WORDS_MAP.putAll(ETALON_FILTRED_WORDS_MAP);
		this.ETALON_WORDS_MAP.put("client", 1);
		this.ETALON_WORDS_MAP.put("on", 1);
		this.ETALON_WORDS_MAP.put("run", 1);
		this.ETALON_WORDS_MAP.put("intended", 1);
		this.ETALON_WORDS_MAP.put("is", 1);
		this.ETALON_WORDS_MAP.put("code", 1);
		this.ETALON_WORDS_MAP.put("which", 1);
		this.ETALON_WORDS_MAP.put("identify", 1);
		this.ETALON_WORDS_MAP.put("easy", 1);
		this.ETALON_WORDS_MAP.put("it", 1);
		this.ETALON_WORDS_MAP.put("make", 1);
		this.ETALON_WORDS_MAP.put("encouraged", 1);
		this.ETALON_WORDS_MAP.put("are", 1);
		this.ETALON_WORDS_MAP.put("conventions", 1);
		this.ETALON_WORDS_MAP.put("particular", 1);
		this.ETALON_WORDS_MAP.put("however", 1);
		this.ETALON_WORDS_MAP.put("ways", 1);
		this.ETALON_WORDS_MAP.put("of", 1);
		this.ETALON_WORDS_MAP.put("variety", 1);
		this.ETALON_WORDS_MAP.put("a", 1);
		this.ETALON_WORDS_MAP.put("organized", 1);
		this.ETALON_WORDS_MAP.put("be", 1);
		this.ETALON_WORDS_MAP.put("can", 1);
		this.ETALON_WORDS_MAP.put("projects", 1);
		this.ETALON_WORDS_MAP.put("articles", 1);
		this.ETALON_WORDS_MAP.put("reference", 1);
		this.ETALON_WORDS_MAP.put("see", 1);
		this.ETALON_WORDS_MAP.put("details", 1);
		this.ETALON_WORDS_MAP.put("technical", 1);
		this.ETALON_WORDS_MAP.put("specific", 1);
		this.ETALON_WORDS_MAP.put("for", 1);
		this.ETALON_WORDS_MAP.put("quick", 1);
		this.ETALON_WORDS_MAP.put("try", 1);
		this.ETALON_WORDS_MAP.put("want", 1);
		this.ETALON_WORDS_MAP.put("might", 1);
		this.ETALON_WORDS_MAP.put("you", 1);
		this.ETALON_WORDS_MAP.put("immediately", 1);
		this.ETALON_WORDS_MAP.put("playing", 1);
		this.ETALON_WORDS_MAP.put("eager", 1);
		this.ETALON_WORDS_MAP.put("new", 1);
		this.ETALON_WORDS_MAP.put("you're", 1);
		this.ETALON_WORDS_MAP.put("if", 1);
		this.ETALON_WORDS_MAP.put("note", 1);
		this.ETALON_WORDS_MAP.put("application", 1);
		this.ETALON_WORDS_MAP.put("your", 1);
		this.ETALON_WORDS_MAP.put("publishing", 1);
		this.ETALON_WORDS_MAP.put("optimizing", 1);
		this.ETALON_WORDS_MAP.put("testing", 1);
		this.ETALON_WORDS_MAP.put("debugging", 1);
		this.ETALON_WORDS_MAP.put("coding", 1);
		this.ETALON_WORDS_MAP.put("organization", 1);
		this.ETALON_WORDS_MAP.put("project", 1);
		this.ETALON_WORDS_MAP.put("span", 1);
		this.ETALON_WORDS_MAP.put("welcome", 1);
		this.ETALON_WORDS_MAP.put("developer's", 1);
		this.ETALON_WORDS_MAP.put("introduces", 1);
		this.ETALON_WORDS_MAP.put("key", 1);
		this.ETALON_WORDS_MAP.put("concepts", 1);
		this.ETALON_WORDS_MAP.put("tools", 1);
		this.ETALON_WORDS_MAP.put("libraries", 1);
		this.ETALON_WORDS_MAP.put("you'll", 1);
		this.ETALON_WORDS_MAP.put("encounter", 1);
		this.ETALON_WORDS_MAP.put("when", 1);
		this.ETALON_WORDS_MAP.put("building", 1);
		this.ETALON_WORDS_MAP.put("applications", 1);
		this.ETALON_WORDS_MAP.put("with", 1);
		this.ETALON_WORDS_MAP.put("topics", 1);

	}

	/**
	 * Тестирование метода фильтрации текста
	 */
	@Test
	public void testFilterText() {

		try {

			// получаем метод из тестируемого класса по названию и типу входных
			// данных
			Method methodFilterText = TextAnalyzerImp.class.getDeclaredMethod("filterText", String.class);

			// включаем игнор проверки идентификатора доступа (так как наш метод
			// private)
			methodFilterText.setAccessible(true);

			// проводит сверку результата работы метода с эталонным результатом
			Assert.assertArrayEquals("Фильтр текста работает некорректно -> filterText(String text)",
					new String[] { (String) methodFilterText.invoke(new TextAnalyzerImp(), TEXT_FOR_TEST) },
					new String[] { ETALON_TEXT });

		} catch (Exception e) {

			// В случае пойманного исключения выдаёт ошибку теста
			Assert.fail(e.getMessage());

		}

	}

	/**
	 * Тестирование метода формирования Map<слво, количество> из текста
	 */
	@Test
	public void testGetWordsMap() {

		try {

			// получаем метод из тестируемого класса по названию и типу входных
			// данных
			Method methodGetWordsMap = TextAnalyzerImp.class.getDeclaredMethod("getWordsMap", String.class);

			// включаем игнор проверки идентификатора доступа (так как наш метод
			// private)
			methodGetWordsMap.setAccessible(true);

			// проводит сверку результата работы метода с эталонным результатом
			Assert.assertArrayEquals("Текст разбивается на слова не корректно -> getWordsMap(String text)",
					new Object[] { methodGetWordsMap.invoke(new TextAnalyzerImp(), ETALON_TEXT) },
					new Object[] { ETALON_WORDS_MAP });

		} catch (Exception e) {

			// В случае пойманного исключения выдаёт ошибку теста
			Assert.fail(e.getMessage());
		}

	}

	/**
	 * Тестирование метода вычисления количества слов в тексте
	 */
	@Test
	public void testGetCountOfWords() {

		try {

			// получаем метод из тестируемого класса по названию и типу входных
			// данных
			Method methodGetCountOfWords = TextAnalyzerImp.class.getDeclaredMethod("getCountOfWords", Map.class);

			// включаем игнор проверки идентификатора доступа (так как наш метод
			// private)
			methodGetCountOfWords.setAccessible(true);

			// проводит сверку результата работы метода с эталонным результатом
			Assert.assertArrayEquals(
					"Количество слов подсчитывается некорректно -> getCountOfWords(Map<String, Integer> wordsMap)",
					new Object[] { methodGetCountOfWords.invoke(new TextAnalyzerImp(), ETALON_WORDS_MAP) },
					new Object[] { ETALON_COUNT_OF_WORDS });

		} catch (Exception e) {

			// В случае пойманного исключения выдаёт ошибку теста
			Assert.fail(e.getMessage());
		}

	}

	/**
	 * Тестирование метода вычисления процентов
	 */
	@Test
	public void testPercent() {

		try {

			// получаем метод из тестируемого класса по названию и типу входных
			// данных
			Method mthd = TextAnalyzerImp.class.getDeclaredMethod("getPercent", int.class, int.class);

			// включаем игнор проверки идентификатора доступа (так как наш метод
			// private)
			mthd.setAccessible(true);

			// проводит сверку результата работы метода с эталонным результатом
			Assert.assertArrayEquals(
					"Проценты вичисляются некорректно -> getPercent(int number100Percents, int numberXPercents)",
					new int[] { (int) mthd.invoke(new TextAnalyzerImp(), ETALON_COUNT_OF_WORDS, ETALON_PERCENT) },
					new int[] { ETALON_PERCENT });

		} catch (Exception e) {

			// В случае пойманного исключения выдаёт ошибку теста
			Assert.fail(e.getMessage());

		}
	}

	/**
	 * Тестирование метода фильтрации Map<слво, количество> с учётом процента
	 * понимания
	 */
	@Test
	public void testFilterWordsMap() {

		try {

			// получаем метод из тестируемого класса по названию и типу входных
			// данных
			Method merthodFilterWordsMap = TextAnalyzerImp.class.getDeclaredMethod("filterWordsMap", Map.class,
					int.class, int.class);

			// включаем игнор проверки идентификатора доступа (так как наш метод
			// private)
			merthodFilterWordsMap.setAccessible(true);

			// проводит сверку результата работы метода с эталонным результатом
			Assert.assertArrayEquals("dhgsdgcshc", new Object[] { merthodFilterWordsMap.invoke(new TextAnalyzerImp(),
					ETALON_WORDS_MAP, ETALON_COUNT_OF_WORDS, ETALON_PERCENT) },
					new Object[] { ETALON_FILTRED_WORDS_MAP });

		} catch (Exception e) {

			// В случае пойманного исключения выдаёт ошибку теста
			Assert.fail(e.getMessage());
		}
	}
}
