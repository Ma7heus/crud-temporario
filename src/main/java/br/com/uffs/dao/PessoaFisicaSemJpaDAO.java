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
				ps = con.prepareStatement("SELECT * FROM PESSOAFISICA WHERE IDPESSOAFISICA = ?");
				ps.setLong(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					u.setIdPessoaFisica(id);
					u.setEmail(rs.getString("email"));
					u.setUsuario(rs.getString("usuario"));
					u.setPermissoes(listPermissoesUsuario(u));
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
	    	List<PessoaFisica> usuarios = new ArrayList<PessoaFisica>();
	    	Connection con = null;
	    	PreparedStatement ps = null;
	    	ResultSet rs = null;
	    	try {
				con = this.ds.getConnection();
				ps = con.prepareStatement("SELECT id_usuario, usuario, email FROM usuario");
				rs = ps.executeQuery();
				while (rs.next()) {
					PessoaFisica u = new PessoaFisica();
					u.setEmail(rs.getString("email"));
					u.setId(rs.getInt("id_usuario"));
					u.setUsuario(rs.getString("usuario"));
					u.setPermissoes(listPermissoesUsuario(u));
					usuarios.add(u);
				}
			} catch (SQLException e) {e.printStackTrace();
			} finally {
				DbUtil.closeResultSet(rs);
				DbUtil.closePreparedStatement(ps);
				DbUtil.closeConnection(con);
			}
	        return usuarios;
	    }
	
}
