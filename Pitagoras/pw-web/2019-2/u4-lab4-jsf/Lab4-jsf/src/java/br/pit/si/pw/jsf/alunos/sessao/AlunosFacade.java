/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pit.si.pw.jsf.alunos.sessao;

import br.pit.si.pw.jsf.alunos.modelo.Alunos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class AlunosFacade extends AbstractFacade<Alunos> {

    @PersistenceContext(unitName = "Lab4-jsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlunosFacade() {
        super(Alunos.class);
    }
    
}
