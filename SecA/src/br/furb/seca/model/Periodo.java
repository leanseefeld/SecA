package br.furb.seca.model;

public enum Periodo {
	_1("07:30", "08:20"), //
	_2("08:20", "09:10"), //
	_3("09:30", "10:20"), //
	_4("10:20", "11:10"), //
	_5("11:10", "12:00"), //
	// não tem o 6
	_7("13:30", "14:20"), //
	_8("14:20", "15:10"), //
	_9("15:30", "16:20"), //
	_10("16:20", "17:10"), //
	_11("17:10", "18:00"), //
	_12("18:30", "19:20"), //
	_13("19:20", "20:10"), //
	_14("20:20", "21:10"), //
	_15("21:10", "22:00"); //

	private String inicio;
	private String fim;

	private Periodo(String inicio, String fim) {
		this.inicio = inicio;
		this.fim = fim;
	}

	public String getFim() {
		return fim;
	}

	public String getInicio() {
		return inicio;
	}
}
