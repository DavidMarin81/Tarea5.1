package modelo.servicio.departamento;

import java.util.List;

import modelo.Departamento;
import modelo.dao.departamento.DepartamentoEXistDao;
import modelo.dao.departamento.IDepartamentoDao;
import modelo.exceptions.DuplicateInstanceException;
import modelo.exceptions.InstanceNotFoundException;

public class ServicioDepartamento implements IServicioDepartamento {

	private IDepartamentoDao departamentoDao;

	public ServicioDepartamento() {
		departamentoDao = new DepartamentoEXistDao();
	}

	@Override
	public boolean create(Departamento dept) throws DuplicateInstanceException {
		
		if (exists(dept)) {
			throw new DuplicateInstanceException(null, dept, Departamento.class.getName());
		} 
		
		return departamentoDao.create(dept);		

	}
	
	public boolean exists(Departamento dept) throws DuplicateInstanceException {
		boolean exists = false;
		Departamento dep;
		try {
			dep = read(dept.getDeptno());
			if (dep.getDeptno() == dept.getDeptno()) {
				exists = true;
				throw new DuplicateInstanceException(null, dep, null);
			}
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exists;
	}

	@Override
	public boolean delete(Departamento dept) {
		return departamentoDao.delete(dept);
	}

	@Override
	public boolean update(Departamento dept) {
		return departamentoDao.update(dept);
	}

	@Override
	public List<Departamento> findAll() {
		return departamentoDao.findAll();
	}

	

	@Override
	public Departamento read(long deptno) throws InstanceNotFoundException {
		return departamentoDao.read(deptno);
	}

}
