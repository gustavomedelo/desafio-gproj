package br.com.desafio.gproj.model;

import br.com.desafio.gproj.enums.RiskEnum;
import br.com.desafio.gproj.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projeto")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "data_inicio")
    private LocalDate initDate;
    @Column(name = "data_previsao_fim")
    private LocalDate endDateForecast;
    @Column(name = "data_fim")
    private LocalDate endDate;
    @Column(name = "descricao")
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Column(name = "orcamento")
    private Float budget;
    @Column(name = "risco")
    @Enumerated(EnumType.STRING)
    private RiskEnum risk;

    @OneToOne
    @JoinColumn(name = "idgerente", referencedColumnName = "id")
    private Person manager;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "membros",
            joinColumns = @JoinColumn(name = "id_projeto"),
            inverseJoinColumns = @JoinColumn(name = "id_pessoa"))
    private List<Person> members;
}
