package login.modelo;

import java.util.ArrayList;
import java.util.List;

public class Question {

	private final int id;
	private final String header;
	private final String[] options;
	private final int correctAnswer;

	public Question(int id, String header, String[] options, int correctAnswer) {
		this.id = id;
		this.header = header;
		this.options = options;
		this.correctAnswer = correctAnswer;
	}

	public Question(int id, String headerRawText, String answerRawText) {
		this(id, headerRawText, new String[] { answerRawText }, 0);
	}

	public int getId() {
		return id;
	}

	public String getHeader() {
		return header;
	}

	public String[] getOptions() {
		return options;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public Question validated() {
		if (this.header == null || this.header.isBlank()) {
			throw new RuntimeException("A questão tem um enunciado em branco ou nulo.");
		}
		if (this.options[0] == null || this.options[0].isBlank()) {
			throw new RuntimeException("A questão tem uma resposta branca ou nula.");
		}

		String[] split = this.header.split("\\r?\\n");
		for (int i = 0; i < split.length; i++) {
			split[i] = split[i].trim();
		}

		if (split.length < 2) {
			throw new RuntimeException("A questão precisa ter duas ou mais linhas para ser válida.");
		}

		char lastAnswerLetter = Character.toUpperCase(split[split.length - 1].charAt(0));
		int countAnswers = switch (lastAnswerLetter) {
			case 'A' -> 1;
			case 'B' -> 2;
			case 'C' -> 3;
			case 'D' -> 4;
			case 'E' -> 5;
			default ->
				throw new RuntimeException("A questão não tem uma última linha que comece com A, B, C, D, ou E.");
		};

		if (split.length + 1 < countAnswers) {
			throw new RuntimeException("A questão precisa ter pelo menos " + (split.length + 1)
					+ " linhas, pois a primeira letra da última linha é " + lastAnswerLetter);
		}

		String header = split[0];
		for (int i = 1; i < split.length - countAnswers; i++) {
			header += "\n" + split[i];
		}

		char answer = Character.toUpperCase(this.options[0].trim().charAt(0));
		int correctAnswer = switch (answer) {
			case 'A' -> 0;
			case 'B' -> 1;
			case 'C' -> 2;
			case 'D' -> 3;
			case 'E' -> 4;
			default ->
				throw new RuntimeException("A questão não tem uma resposta que comece com as letras A, B, C, D ou E.");
		};

		String[] options = new String[countAnswers];
		for (int i = 0; i < options.length; i++) {
			options[i] = split[split.length - i - 1];
		}

		return new Question(id, header, options, correctAnswer);
	}

	public String toRawText() {
		return this.header + "\n " + String.join("\n ", this.options);
	}

	public String getCorrectOption() {
		return this.options[this.correctAnswer];
	}

}
