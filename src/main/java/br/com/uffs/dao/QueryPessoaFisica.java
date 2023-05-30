package br.com.uffs.dao;

public class QueryPessoaFisica {
	
	public String getAllPessoaFisica() {
		StringBuilder query = new StringBuilder();
	
		query.append(" SELECT IDPESSOAFISICA, ");
		query.append(" NOME, NOMESOCIAL, CPF, ALTURA, MASSA, GENERO, IDADE, EMAIL, TELEFONE, ENDERECO  ");
		query.append(" FROM PESSOAFISICA ");
	
		return query.toString();
	}

	public String findById() {
		
		StringBuilder query = new StringBuilder();
		
		query.append(" WHERE IDPESSOAFISICA = ? ");

		return getAllPessoaFisica() + query;
	}
	
	public String insert() {
		StringBuilder query = new StringBuilder();
		
		query.append(" INSERT INTO PESSOAFISICA ( NOME, NOMESOCIAL, CPF, ALTURA, MASSA, GENERO, IDADE, EMAIL, TELEFONE, ENDERECO)  ");
		query.append(" VALUES ( ? , ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
		
		return query.toString();
	}

	public String getFilterQuery(String info) {
		String infoFiltro = " '%" +info+ "%' ";

		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM PESSOAFISICA WHERE LOWER(NOME) LIKE " + infoFiltro);
		query.append(" OR LOWER(NOMESOCIAL) LIKE " + infoFiltro);
		query.append(" OR LOWER(GENERO) LIKE " + infoFiltro);
		query.append(" OR LOWER(EMAIL) LIKE " + infoFiltro);
		query.append(" OR LOWER(ENDERECO) LIKE " + infoFiltro);
		
//		boolean isNumber = isNumeric(info);
//		if(isNumber) {
//			query.append(" OR IDADE = " + info);	
//			query.append(" OR MASSA = " + info);
//			query.append(" OR ALTURA = " + info);
//		}
       
		return query.toString();
    }

    public static boolean isNumeric(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
