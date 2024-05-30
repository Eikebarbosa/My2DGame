/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.telas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import login.db.QuestionDAO;

/**
 *
 * @author schix
 */
public class AdminTela extends javax.swing.JFrame {
    
    public AdminTela() {
        // Configurando a janela principal
        setTitle("Painel de Chaves e Valores");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Key", "Value"}, 0);
        Map<String, String> map = QuestionDAO.getQuestions();
        
        for (Map.Entry<String, String> entry : map.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
        

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JPanel buttonPanel = new JPanel();
        
        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow(new Object[]{"", ""});
            }
        });
        
        // Botão para remover a linha selecionada
        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para remover.");
                }
            }
        });

        // Adicionando os botões ao painel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // Adicionando a tabela e o painel de botões ao frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void addButtonAction(ActionEvent event) {
        
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminTela().setVisible(true);
            }
        });
    }
}
