package emploiDuTemps_java;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Requetes extends JFrame {
    Statement st;
    Connexion con = new Connexion();
    ResultSet rst;
    JTable table2;
    JScrollPane scroll2;
    JLabel lbclasse, lbmatiere, lbtitre, lbtitre2, lbid, lbclasse2;
    JTextField tfmatiere, tfid;
    JComboBox<String> comboclasse, comboclasse2;
    JButton btrech, btsupp, btrech2, btannuler1, btannuler2, btannuler3;

    public Requetes() {
        this.setTitle("wiem");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        JPanel pn = new JPanel();
        pn.setLayout(null);
        add(pn);
        pn.setBackground(new Color(173, 216, 230));

       
        lbtitre2 = new JLabel("Emploi du temps de la semaine par classe");
        lbtitre2.setBounds(20, 5, 800, 30);
        lbtitre2.setFont(new Font("Times New Roman", Font.BOLD, 21));
        pn.add(lbtitre2);

        // Classe 2
        lbclasse2 = new JLabel("Classe");
        lbclasse2.setBounds(30, 40, 150, 25);
        lbclasse2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        pn.add(lbclasse2);

        comboclasse2 = new JComboBox<>();
        comboclasse2.addItem("");
        comboclasse2.addItem("1erelicence");
        comboclasse2.addItem("2emelicence");
        comboclasse2.addItem("3emelicence");
        comboclasse2.addItem("1eremaster");
        comboclasse2.addItem("2ememaster");
        comboclasse2.addItem("1ereING");
        comboclasse2.addItem("2emeING");
        comboclasse2.setBounds(100, 40, 150, 25);
        pn.add(comboclasse2);

        // Bouton Chercher 2
        btrech2 = new JButton("CHERCHER");
        btrech2.setBounds(260, 40, 120, 25);
        btrech2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String classe = comboclasse2.getSelectedItem().toString();

                if (classe.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Le champ classe est obligatoire.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DefaultTableModel df2 = new DefaultTableModel();
                init2();
                pn.add(scroll2);
                df2.addColumn("ID");
                df2.addColumn("Classe");
                df2.addColumn("Jour");
                df2.addColumn("Matière");
                df2.addColumn("Heure");
                df2.addColumn("Nom enseignant");
                df2.addColumn("Contact enseignant");
                table2.setModel(df2);
                String rq2 = "SELECT * FROM enseignant_cours WHERE classe='" + classe + "' ORDER BY num_jour, heure";

                try {
                    st = con.laConnection().createStatement();
                    rst = st.executeQuery(rq2);
                    if (!rst.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "Aucune donnée trouvée pour la classe spécifiée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    while (rst.next()) {
                        df2.addRow(new Object[]{
                            rst.getString("id"), rst.getString("classe"), rst.getString("jour"),
                            rst.getString("matiere"), rst.getString("heure"), rst.getString("nom"), rst.getString("contact")
                        });
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'exécution de la requête.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pn.add(btrech2);

        
        btannuler3 = new JButton("ANNULER");
        btannuler3.setBounds(390, 40, 120, 25);
        btannuler3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboclasse2.setSelectedIndex(0);
            }
        });
        pn.add(btannuler3);

     
        lbtitre = new JLabel("Les séances de cours dans la semaine d'une matière dans une classe");
        lbtitre.setBounds(20, 80, 800, 30);
        lbtitre.setFont(new Font("Times New Roman", Font.BOLD, 21));
        pn.add(lbtitre);

      
        lbclasse = new JLabel("Classe");
        lbclasse.setBounds(30, 120, 150, 25);
        lbclasse.setFont(new Font("Times New Roman", Font.BOLD, 18));
        pn.add(lbclasse);

        comboclasse = new JComboBox<>();
        comboclasse.addItem("");
        comboclasse.addItem("1erelicence");
        comboclasse.addItem("2emelicence");
        comboclasse.addItem("3emelicence");
        comboclasse.addItem("1eremaster");
        comboclasse.addItem("2ememaster");
        comboclasse.addItem("1ereING");
        comboclasse.addItem("2emeING");
        comboclasse.addItem("1emeING");
        comboclasse.setBounds(30, 150, 150, 25);
        pn.add(comboclasse);

        // Matière
        lbmatiere = new JLabel("Matière");
        lbmatiere.setBounds(200, 120, 150, 25);
        lbmatiere.setFont(new Font("Times New Roman", Font.BOLD, 18));
        pn.add(lbmatiere);

        tfmatiere = new JTextField();
        tfmatiere.setBounds(200, 150, 150, 25);
        pn.add(tfmatiere);

        // Bouton Chercher 1
        btrech = new JButton("CHERCHER");
        btrech.setBounds(360, 150, 120, 25);
        btrech.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String classe = comboclasse.getSelectedItem().toString();
                String matiere = tfmatiere.getText();

                if (classe.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Le champ classe est obligatoire.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (matiere.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Le champ matière est obligatoire.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DefaultTableModel df2 = new DefaultTableModel();
                init2();
                pn.add(scroll2);
                df2.addColumn("ID");
                df2.addColumn("Classe");
                df2.addColumn("Matière");
                df2.addColumn("Jour");
                df2.addColumn("Heure");
                df2.addColumn("Nom enseignant");
                df2.addColumn("Contact enseignant");
                table2.setModel(df2);
                String rq2 = "SELECT * FROM enseignant_cours WHERE classe='" + classe + "' AND matiere='" + matiere + "' ORDER BY num_jour";

                try {
                    st = con.laConnection().createStatement();
                    rst = st.executeQuery(rq2);
                    if (!rst.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "La matiére n'existe pas saisir une autre ", "Information", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    while (rst.next()) {
                        df2.addRow(new Object[]{
                            rst.getString("id"), rst.getString("classe"), rst.getString("matiere"),
                            rst.getString("jour"), rst.getString("heure"), rst.getString("nom"), rst.getString("contact")
                        });
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'exécution de la requête.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pn.add(btrech);

        // Bouton Annuler 1
        btannuler1 = new JButton("ANNULER");
        btannuler1.setBounds(490, 150, 120, 25);
        btannuler1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboclasse.setSelectedIndex(0);
                tfmatiere.setText("");
            }
        });
        pn.add(btannuler1);

        // ID
        lbid = new JLabel("ID");
        lbid.setBounds(20, 200, 150, 25);
        lbid.setFont(new Font("Times New Roman", Font.BOLD, 18));
        pn.add(lbid);

        tfid = new JTextField();
        tfid.setBounds(50, 200, 90, 25);
        pn.add(tfid);

        // Bouton Supprimer
        btsupp = new JButton("SUPPRIMER");
        btsupp.setBounds(150, 200, 110, 25);
        btsupp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String id = tfid.getText();

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Le champ ID est obligatoire.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String rq = "SELECT * FROM tb_cours WHERE id='" + id + "'";

                try {
                    st = con.laConnection().createStatement();
                    rst = st.executeQuery(rq);
                    if (!rst.next()) {
                        JOptionPane.showMessageDialog(null, "L'ID spécifié n'existe pas saisir un autre", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    rq = "DELETE FROM tb_cours WHERE id='" + id + "'";
                    if (JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer la séance de cours ?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        st.executeUpdate(rq);
                        JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès !", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pn.add(btsupp);

    
        btannuler2 = new JButton("ANNULER");
        btannuler2.setBounds(270, 200, 110, 25);
        btannuler2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfid.setText("");
            }
        });
        pn.add(btannuler2);
    }

    private void init2() {
        table2 = new JTable();
        scroll2 = new JScrollPane();
        scroll2.setBounds(10, 250, 770, 200);
        scroll2.setViewportView(table2);
    }

   
}
