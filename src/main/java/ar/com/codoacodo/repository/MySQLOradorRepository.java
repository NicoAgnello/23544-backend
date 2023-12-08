package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.utils.DateUtils;

public class MySQLOradorRepository implements OradorRepository {

	@Override
	public void save(Orador orador) {
		//1 -- Obtengo la conexion
		
//		Connection con = AdministradorDeConexiones.getConnection(); ---> se pasa como parametro dentro del try para cerrar la conexion luego.
		
		//2 -- preparo la sentencia de sql (prepareStatement) // Los signos de pregunta evita sql injection;
		
		String sql = "insert into orador (nombre, apellido, tema, mail, fecha_alta) values (?,?,?,?,?)"; 
		
		// Armo la sentencia preparada
		
		
		try (Connection con = AdministradorDeConexiones.getConnection();) {
			
			
			PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getTema());
			statement.setString(4, orador.getEmail());
			statement.setDate(5, new java.sql.Date(DateUtils.asDate(orador.getFechaAlta()).getTime())); // tph: ver comop pasar de LocalDate a java.sql.date porque asi no funciona
			
			statement.executeUpdate(); // INSERT ? UPDATE ? DELETE
			
			ResultSet res = statement.getGeneratedKeys();
			
			if(res.next()) {
				Long id = res.getLong(1); // aca esta el id;
				orador.setId(id);
			}
			
		}catch (Exception e) {
			throw new IllegalArgumentException("No se pudo crear el orador: ", e);
		}
	}

	@Override
	public Orador getById(Long id) {
		

		String sql = "select id_orador, nombre, apellido, tema, mail, fecha_alta from orador where id_orador = ?";

		Orador orador = null;
		try(Connection con = AdministradorDeConexiones.getConnection();) {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setLong(1, id);

			ResultSet res = statement.executeQuery();// SELECT

			if (res.next()) {
				Long dbId = res.getLong(1);  
				String nombre = res.getString(2);  
				String apellido = res.getString(3);  
				String tema = res.getString(4);  
				String email = res.getString(5);  
				Date fechaAlta = res.getDate(6);  
				
				orador = new Orador(dbId, nombre, apellido, email, tema, fechaAlta.toLocalDate());
			}
			
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo traer el orador:", e);
		}
		return orador;
	}


	@Override
	public void update(Orador orador) {
		String sql = "update orador" + " set nombre = ? , apellido = ?, mail = ?, tema = ?" + "where id_orador = ?";
		
		try(Connection con = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getEmail());
			statement.setString(4, orador.getTema());
			statement.setLong(5, orador.getId());
			
			statement.executeUpdate();		
			
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo actualizar el orador:", e);
		}

	}

	@Override
	public void delete(Long id) {
		String sql = "delete from orador where id_orador = ?";
		
		try(Connection con = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = con.prepareStatement(sql);

			statement.setLong(1, id);
			
			statement.executeUpdate();		
			
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo borrar el orador:", e);
		}
	}

	@Override
	public List<Orador> findAll() {

		String sql = "select id_orador, nombre, apellido, tema, mail, fecha_alta from orador";

		List<Orador> oradores = new ArrayList<>();//se ve bien en spring!
		
		//try with resources
		try(Connection con = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet res = statement.executeQuery();// SELECT

			while (res.next()) {
				Long dbId = res.getLong(1);  
				String nombre = res.getString(2);  
				String apellido = res.getString(3);  
				String tema = res.getString(4);  
				String email = res.getString(5);  
				LocalDate fechaAlta = DateUtils.asLocalDate(res.getDate(6));

				
				oradores.add(new Orador(dbId, nombre, apellido, email, tema,fechaAlta));
			}
			
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo traer los oradores:", e);
		}
		return oradores;
	}

}
