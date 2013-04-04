/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.indra_prasnikar.m2lt3.bo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author rpadmin
 */
@Entity @Data
public class Buch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private String id;
    @Getter @Setter private String titel;
    @Getter @Setter private Integer seiten;

    public Buch() {
        this.titel = "ABC";
        this.seiten = 10;
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
        if (!(object instanceof Buch)) {
            return false;
        }
        Buch other = (Buch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.indra_prasnikar.m2lt3.bo.Buch[ id=" + id + " ]";
    }
    
}
