//package edu.uc.eh.uniprot;
//
///**
// * Created by shamsabz on 12/24/18.
// */
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class UniprotRepositoryH2 {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//
//    class UniprotRowMapper implements RowMapper<Uniprot> {
//        @Override
//        public Uniprot mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Uniprot uniprot = new Uniprot();
//
//            uniprot.setId(rs.getInt("id"));
//
//            uniprot.setFamily(rs.getString("family"));
//            uniprot.setSequence(rs.getString("sequence"));
//            uniprot.setSubcellular_location(rs.getString("subcellular_location"));
//            uniprot.setAccession(rs.getString("accession"));
//            uniprot.setInteractant(rs.getString("interactant"));
//            uniprot.setGo(rs.getString("go"));
//            uniprot.setNucleotide_phosphate_binding_region(rs.getString("nucleotide_phosphate_binding_region"));
//            uniprot.setSynonym_gene_names(rs.getString("synonym_gene_names"));
//            uniprot.setActive_sites(rs.getString("active_sites"));
//            uniprot.setLength_seq(rs.getString("length_seq"));
//            uniprot.setTissue_specificity(rs.getString("tissue_specificity"));
//            uniprot.setDrug_bank(rs.getString("drug_bank"));
//            uniprot.setBinding_sites(rs.getString("binding_sites"));
//            uniprot.setPrimary_gene_name(rs.getString("primary_gene_name"));
//            uniprot.setFunction(rs.getString("function"));
//            uniprot.setKegg(rs.getString("kegg"));
//            uniprot.setBinding_db(rs.getString("binding_db"));
//            uniprot.setStringId(rs.getString("string_id"));
//            uniprot.setChembl(rs.getString("chembl"));
//            uniprot.setAccession_all(rs.getString("accession_all"));
//            uniprot.setEnsembl(rs.getString("ensembl"));
//            uniprot.setFull_name(rs.getString("full_name"));
//            uniprot.setSub_family(rs.getString("sub_family"));
//            uniprot.setActivity_regulation(rs.getString("activity_regulation"));
//            uniprot.setSuperfamily(rs.getString("superfamily"));
//            uniprot.setName(rs.getString("name"));
//            uniprot.setUcsc(rs.getString("ucsc"));
//            uniprot.setGene_id(rs.getString("gene_id"));
//            uniprot.setDomains(rs.getString("domains"));
//            uniprot.setReactome(rs.getString("reactome"));
//            uniprot.setOrganism(rs.getString("organism"));
//            uniprot.setHgnc(rs.getString("hgnc"));
//            return uniprot;
//        }
//
//    }
//
//    public List<Uniprot> findAll() {
//        return jdbcTemplate.query("select * from uniprot", new UniprotRowMapper());
//    }
//
//    public Uniprot findByName(String name) {
//        return jdbcTemplate.queryForObject("select * from uniprot where name=?", new Object[] { name },
//                new BeanPropertyRowMapper<Uniprot>(Uniprot.class));
//    }
//    public Uniprot findByAccession(String accession) {
//        System.out.print(accession);
//        System.out.print(findAll());
//        return jdbcTemplate.queryForObject("select * from uniprot where accession=?", new Object[] { accession },
//                new BeanPropertyRowMapper<Uniprot>(Uniprot.class));
//    }
//    public int deleteByAccession(String accession) {
//        return jdbcTemplate.update("delete from uniprot where accession=?", new Object[] { accession });
//    }
//    public int deleteByName(String name) {
//        return jdbcTemplate.update("delete from uniprot where name=?", new Object[] { name });
//    }
//
//    public int insert(Uniprot uniprot) {
//        return jdbcTemplate.update("insert into uniprot (id, family,sequence, subcellular_location, accession, interactant, go, nucleotide_phosphate_binding_region, synonym_gene_names, active_sites, length_seq, tissue_specificity, drug_bank, binding_sites, primary_gene_name, function, kegg, binding_db, string_id, chembl, accession_all, ensembl, full_name, sub_family, activity_regulation, superfamily, name, ucsc, gene_id, domains, reactome, organism, hgnc) " + "values(?,  ?, ?,?,  ?, ?,?,  ?, ?,?,  ?, ?,?,  ?, ?,?,  ?, ?,?,  ?, ?,?,  ?, ?,?,  ?, ?,?,  ?, ?,?,  ?, ?)",
//                new Object[] {
//                        uniprot.getId(),
//                        uniprot.getHgnc(),
//                        uniprot.getFamily(),
//                        uniprot.getSequence(),
//                        uniprot.getSubcellular_location(),
//                        uniprot.getAccession(),
//                        uniprot.getInteractant(),
//                        uniprot.getGo(),
//                        uniprot.getNucleotide_phosphate_binding_region(),
//                        uniprot.getSynonym_gene_names(),
//                        uniprot.getActive_sites(),
//                        uniprot.getLength_seq(),
//                        uniprot.getTissue_specificity(),
//                        uniprot.getDrug_bank(),
//                        uniprot.getBinding_sites(),
//                        uniprot.getPrimary_gene_name(),
//                        uniprot.getFunction(),
//                        uniprot.getKegg(),
//                        uniprot.getBinding_db(),
//                        uniprot.getStringId(),
//                        uniprot.getChembl(),
//                        uniprot.getAccession_all(),
//                        uniprot.getEnsembl(),
//                        uniprot.getFull_name(),
//                        uniprot.getSub_family(),
//                        uniprot.getActivity_regulation(),
//                        uniprot.getSuperfamily(),
//                        uniprot.getName(),
//                        uniprot.getUcsc(),
//                        uniprot.getGene_id(),
//                        uniprot.getDomains(),
//                        uniprot.getReactome(),
//                        uniprot.getOrganism()
//
//                //uniprot.getId(), uniprot.getName(), uniprot.getPassportNumber()
//
//        });
//    }
////
////    public int update(Uniprot uniprot) {
////        return jdbcTemplate.update("update uniprot " + " set name = ?, passport_number = ? " + " where accession = ?",
////                new Object[] {
////
////
////                        uniprot.getHgnc(),
////                        uniprot.getFamily(),
////                        uniprot.getSequence(),
////                        uniprot.getSubcellular_location(),
////                        uniprot.getAccession(),
////                        uniprot.getInteractant(),
////                        uniprot.getGo(),
////                        uniprot.getNucleotide_phosphate_binding_region(),
////                        uniprot.getSynonym_gene_names(),
////                        uniprot.getActive_sites(),
////                        uniprot.getLength(),
////                        uniprot.getTissue_specificity(),
////                        uniprot.getDrug_bank(),
////                        uniprot.getBinding_sites(),
////                        uniprot.getPrimary_gene_name(),
////                        uniprot.getFunction(),
////                        uniprot.getKegg(),
////                        uniprot.getBinding_db(),
////                        uniprot.getString(),
////                        uniprot.getChembl(),
////                        uniprot.getAccession_all(),
////                        uniprot.getEnsembl(),
////                        uniprot.getFull_name(),
////                        uniprot.getSub_family(),
////                        uniprot.getActivity_regulation(),
////                        uniprot.getSuperfamily(),
////                        uniprot.getName(),
////                        uniprot.getUcsc(),
////                        uniprot.getGene_id(),
////                        uniprot.getDomains(),
////                        uniprot.getReactome(),
////                        uniprot.getOrganism()
////
////                //uniprot.getName(), uniprot.getPassportNumber(), uniprot.getId()
////
////
////
////
////        });
////    }
//
////    public void putData() {
////        return jdbcTemplate.update("update student " + " set name = ?, passport_number = ? " + " where id = ?",
////                new Object[] { student.getName(), student.getPassportNumber(), student.getId() });
////    }
//}
//
