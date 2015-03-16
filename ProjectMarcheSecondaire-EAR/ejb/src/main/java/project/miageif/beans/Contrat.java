package project.miageif.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "CONTRAT" )
@NamedQuery(name="Contrat.findContratByID", query="select u from Contrat u where u.id=:id")
public class Contrat {

		public static final String FIND_BY_ID = "Contrat.findContratByID";
		
		@Id @NotNull
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id_contrat")
		private Integer id;
		
		@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="Id_Societe")
		private Societe societe;
		
		@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="id_investisseur")
		private Investisseur user;
		
		@OneToMany(fetch = FetchType.EAGER)
		@Fetch(value = FetchMode.SUBSELECT)
		private List<Offre> offres = new ArrayList<Offre>();
		
		public List<Offre> getOffres() {
			return offres;
		}
		public void setOffres(List<Offre> offres) {
			this.offres = offres;
		}
		private int quantite;
		private Date date;
		
		public Date getDate() {
			return date;
		}
		public int getQuantite() {
			return quantite;
		}
		public Societe getSociete() {
			return societe;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public void setQuantite(int quantite) {
			this.quantite = quantite;
		}
		public void setSociete(Societe societe) {
			this.societe = societe;
		}
		public Investisseur getUser() {
			return user;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public void setUser(Investisseur user) {
			this.user = user;
		}
}
