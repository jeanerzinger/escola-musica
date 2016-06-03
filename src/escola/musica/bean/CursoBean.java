package escola.musica.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import escola.musica.dao.CursoDAO;
import escola.musica.modelo.Curso;
import escola.musica.modelo.TipoCurso;

@ManagedBean
@SessionScoped
public class CursoBean {

	private Curso curso;
	private List<TipoCurso> tipos = Arrays.asList(TipoCurso.values()); 
	private List<Curso> cursos = new ArrayList<Curso>();
	
	
	public CursoBean(){
		curso = new Curso();
		cursos = new CursoDAO().listarTodos();
		
	}
	
	public String salvar(){
		new CursoDAO().salvar(curso);
		curso = new Curso();
		cursos = new CursoDAO().listarTodos();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso."));
		return "curso_lista?faces-redirect=true";
	}
	public String editar(Curso curso){
		this.curso = curso;
		return "curso_formulario?faces-redirect=true";
	}
	
	public void excluir(){
		new CursoDAO().excluir(curso);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso. "+curso.getNome()));
		cursos = new CursoDAO().listarTodos();
	}
	
	public void prepararExclusao(Curso curso){
		this.curso = curso;
	}
	
	public String getDataAtual(){
		return new SimpleDateFormat("dd/MM/yyy").format(new Date());
	}

	public List<TipoCurso> getTipos() {
		return tipos;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public void setTipos(List<TipoCurso> tipos) {
		this.tipos = tipos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
