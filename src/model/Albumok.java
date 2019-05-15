/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximuse
 */
@Entity
@Table(name = "albumok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Albumok.findAll", query = "SELECT a FROM Albumok a")
    , @NamedQuery(name = "Albumok.findById", query = "SELECT a FROM Albumok a WHERE a.id = :id")
    , @NamedQuery(name = "Albumok.findByNev", query = "SELECT a FROM Albumok a WHERE a.nev = :nev")
    , @NamedQuery(name = "Albumok.findByEv", query = "SELECT a FROM Albumok a WHERE a.ev = :ev")
    , @NamedQuery(name = "Albumok.findByBorito", query = "SELECT a FROM Albumok a WHERE a.borito = :borito")})
public class Albumok implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nev")
    private String nev;
    @Basic(optional = false)
    @Column(name = "ev")
    private int ev;
    @Basic(optional = false)
    @Column(name = "borito")
    private String borito;

    public Albumok() {
    }

    public Albumok(Integer id) {
        this.id = id;
    }

    public Albumok(Integer id, String nev, int ev, String borito) {
        this.id = id;
        this.nev = nev;
        this.ev = ev;
        this.borito = borito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getEv() {
        return ev;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public String getBorito() {
        return borito;
    }

    public void setBorito(String borito) {
        this.borito = borito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Albumok)) {
            return false;
        }
        Albumok other = (Albumok) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Albumok[ id=" + id + " ]";
    }
    
}
