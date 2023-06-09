package br.com.uffs.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import br.com.uffs.model.PessoaFisica;
import br.com.uffs.util.DbUtil;

public class PessoaFisicaSemJpaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DataSource ds;

	 public PessoaFisica findById(Long id) {
		 PessoaFisica u = new PessoaFisica();
	    	Connection con = null;
	    	PreparedStatement ps = null;
	    	ResultSet rs = null;
	    	try {
				con = this.ds.getConnection();
				ps = con.prepareStatement(new QueryPessoaFisica().findById());
				ps.setLong(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					u.setIdPessoaFisica(rs.getLong("IDPESSOAFISICA"));
					u.setNome(rs.getString("NOME"));
					u.setNomeSocial(rs.getString("NOMESOCIAL"));
				    u.setCpf(rs.getString("CPF"));
				    u.setAltura(rs.getBigDecimal("ALTURA"));
				    u.setMassa(rs.getBigDecimal("MASSA"));
				    u.setGenero(rs.getString("GENERO"));
				    u.setIdade(rs.getLong("IDADE"));
				    u.setEmail(rs.getString("EMAIL"));
				    u.setTelefone(rs.getString("TELEFONE"));
				    u.setEndereco(rs.getString("ENDERECO"));
				}
			} catch (SQLException e) {e.printStackTrace();
			} finally {
				DbUtil.closeResultSet(rs);
				DbUtil.closePreparedStatement(ps);
				DbUtil.closeConnection(con);
			}
	        return u;
	    }
	
	 public List<PessoaFisica> listAll() {
	    	List<PessoaFisica> pessoaFisicaList = new ArrayList<PessoaFisica>();
	    	Connection con = null;
	    	PreparedStatement ps = null;
	    	ResultSet rs = null;
	    	try {
				con = this.ds.getConnection();
				ps = con.prepareStatement(new QueryPessoaFisica().getAllPessoaFisica());
				rs = ps.executeQuery();
				while (rs.next()) {
					PessoaFisica u = new PessoaFisica();
					u.setIdPessoaFisica(rs.getLong("IDPESSOAFISICA"));
					u.setNome(rs.getString("NOME"));
					u.setNomeSocial(rs.getString("NOMESOCIAL"));
				    u.setCpf(rs.getString("CPF"));
				    u.setAltura(rs.getBigDecimal("ALTURA"));
				    u.setMassa(rs.getBigDecimal("MASSA"));
				    u.setGenero(rs.getString("GENERO"));
				    u.setIdade(rs.getLong("IDADE"));
				    u.setEmail(rs.getString("EMAIL"));
				    u.setTelefone(rs.getString("TELEFONE"));
				    u.setEndereco(rs.getString("ENDERECO"));
				    pessoaFisicaList.add(u);
				}
			} catch (SQLException e) {e.printStackTrace();
			} finally {
				DbUtil.closeResultSet(rs);
				DbUtil.closePreparedStatement(ps);
				DbUtil.closeConnection(con);
			}
	        return pessoaFisicaList;
	    }
	 
	 public Boolean cadastrar(PessoaFisica pessoaFisica) {
		 System.out.println("Chamando SQL CADASTRAR");
		    Boolean resultado = false;
		    Connection con = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    try {
		        con = this.ds.getConnection();
		        con.setAutoCommit(false);
		        try {
		            ps = con.prepareStatement("INSERT INTO PESSOAFISICA (nome, nomesocial, cpf, altura, massa, genero, idade, email, telefone, endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING IDPESSOAFISICA");
		            ps.setString(1, pessoaFisica.getNome());
		            ps.setString(2, pessoaFisica.getNomeSocial());
		            ps.setString(3, pessoaFisica.getCpf());
		            ps.setBigDecimal(4, pessoaFisica.getAltura());
		            ps.setBigDecimal(5, pessoaFisica.getMassa());
		            ps.setString(6, pessoaFisica.getGenero());
		            ps.setLong(7, pessoaFisica.getIdade());
		            ps.setString(8, pessoaFisica.getEmail());
		            ps.setString(9, pessoaFisica.getTelefone());
		            ps.setString(10, pessoaFisica.getEndereco());

		            rs = ps.executeQuery();
		            rs.next();
		            pessoaFisica.setIdPessoaFisica(rs.getLong("IDPESSOAFISICA"));

		            con.commit();
		            resultado = true;
		        } catch (SQLException e) {
		            e.printStackTrace();
		            con.rollback();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DbUtil.closeResultSet(rs);
		        DbUtil.closePreparedStatement(ps);
		        DbUtil.closeConnection(con);
		    }
		    return resultado;
		}

	
	 public boolean deletar(long id) {
		    boolean resultado = false;
		    Connection con = null;
		    PreparedStatement ps = null;
		    
		    try {
		        con = this.ds.getConnection();
		        con.setAutoCommit(false);
		        
		        try {
		            ps = con.prepareStatement("DELETE FROM PESSOAFISICA WHERE IDPESSOAFISICA = ?");
		            ps.setLong(1, id);
		            
		            int rowsAffected = ps.executeUpdate();
		            con.commit();
		            
		            resultado = (rowsAffected > 0); // Check if any rows were affected
		        } catch (SQLException e) {
		            e.printStackTrace();
		            con.rollback();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DbUtil.closePreparedStatement(ps);
		        DbUtil.closeConnection(con);
		    }
		    
		    return resultado;
		}
	
	public boolean atualizar(PessoaFisica pessoaFisica) {
		System.out.println("Chamando SQL ATUALIZAR");
	    boolean resultado = false;
	    Connection con = null;
	    PreparedStatement ps = null;
	    
	    try {
	        con = this.ds.getConnection();
	        con.setAutoCommit(false);
	        
	        try {
	            ps = con.prepareStatement("UPDATE PESSOAFISICA SET nome = ?, nomesocial = ?, cpf = ?, altura = ?, massa = ?, genero = ?, idade = ?, email = ?, telefone = ?, endereco = ? WHERE IDPESSOAFISICA = ?");
	            ps.setString(1, pessoaFisica.getNome());
	            ps.setString(2, pessoaFisica.getNomeSocial());
	            ps.setString(3, pessoaFisica.getCpf());
	            ps.setBigDecimal(4, pessoaFisica.getAltura());
	            ps.setBigDecimal(5, pessoaFisica.getMassa());
	            ps.setString(6, pessoaFisica.getGenero());
	            ps.setLong(7, pessoaFisica.getIdade());
	            ps.setString(8, pessoaFisica.getEmail());
	            ps.setString(9, pessoaFisica.getTelefone());
	            ps.setString(10, pessoaFisica.getEndereco());
	            ps.setLong(11, pessoaFisica.getIdPessoaFisica());
	            
	            int rowsAffected = ps.executeUpdate();
	            con.commit();
	            
	            resultado = (rowsAffected > 0); // Check if any rows were affected
	        } catch (SQLException e) {
	            e.printStackTrace();
	            con.rollback();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DbUtil.closePreparedStatement(ps);
	        DbUtil.closeConnection(con);
	    }
	    
	    return resultado;
	}
	
	 public List<PessoaFisica> filtrar(String filtro) {
	        List<PessoaFisica> pessoaFisicaList = new ArrayList<>();
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        
	        try {
	            con = this.ds.getConnection();
	            ps = con.prepareStatement(new QueryPessoaFisica().getFilterQuery(filtro.toString()));
	            //ps.setString(1, "%" + filtro + "%");
	            rs = ps.executeQuery();
	            
	            while (rs.next()) {
	                PessoaFisica u = new PessoaFisica();
	                u.setIdPessoaFisica(rs.getLong("IDPESSOAFISICA"));
	                u.setNome(rs.getString("NOME"));
	                u.setNomeSocial(rs.getString("NOMESOCIAL"));
	                u.setCpf(rs.getString("CPF"));
	                u.setAltura(rs.getBigDecimal("ALTURA"));
	                u.setMassa(rs.getBigDecimal("MASSA"));
	                u.setGenero(rs.getString("GENERO"));
	                u.setIdade(rs.getLong("IDADE"));
	                u.setEmail(rs.getString("EMAIL"));
	                u.setTelefone(rs.getString("TELEFONE"));
	                u.setEndereco(rs.getString("ENDERECO"));
	                pessoaFisicaList.add(u);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DbUtil.closeResultSet(rs);
	            DbUtil.closePreparedStatement(ps);
	            DbUtil.closeConnection(con);
	        }
	        
	        return pessoaFisicaList;
	    }
	
}
