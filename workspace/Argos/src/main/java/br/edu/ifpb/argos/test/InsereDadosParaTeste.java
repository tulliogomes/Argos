package br.edu.ifpb.argos.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import br.edu.ifpb.argos.dao.ManagedEMContext;
import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.dao.UsuarioDAO;
import br.edu.ifpb.argos.entity.Usuario;

public class InsereDadosParaTeste {
	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeClass
	public static void init() {
		PersistenceUtil.createEntityManagerFactory("argos");
		emf = PersistenceUtil.getEntityManagerFactory();
		ManagedEMContext.bind(emf, emf.createEntityManager());
		System.out.println("init()");
	}

	@AfterClass
	public static void destroy() {
		if (emf != null) {
			emf.close();
		}
	}

	@Before
	public void initEM() {
		em = emf.createEntityManager();
	}

	@Test
	public void testInsereUsuarios() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Usuario usuario1 = new Usuario();
		usuario1.setMatricula("123456");
		usuario1.setNome("argos");
		usuario1.setEmail("argos@ifpb.edu.br");
		usuario1.setSenha("123456");
		usuario1.setFone("83996193926");
		Date aniversario1 = sdf.parse("1981-06-15");
		usuario1.setDataAniversario(aniversario1);
		usuario1.setStatus(true);
		usuario1.setPerfil("funcionario");

		UsuarioDAO udao = new UsuarioDAO(em);
		udao.beginTransaction();
		udao.insert(usuario1);
		udao.commit();

	}
}