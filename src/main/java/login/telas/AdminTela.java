/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.telas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import login.db.QuestionDAO;

/**
 *
 * @author schix
 */
public class AdminTela extends javax.swing.JFrame {
    
    public AdminTela() {
        // Configurando a janela principal
        setTitle("Painel Admin");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel status = new JLabel(" ");
        DefaultTableModel model = new DefaultTableModel(new Object[] {"Pergunta", "Resposta"}, 0);
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
                                JPanel panel = new JPanel(new GridLayout(2, 2));
                JTextField keyField = new JTextField();
                JTextField valueField = new JTextField();

                panel.add(new JLabel("Pergunta:"));
                panel.add(keyField);
                panel.add(new JLabel("Resposta:"));
                panel.add(valueField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar pergunta e resposta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    String key = keyField.getText();
                    String value = valueField.getText();

                    if (!key.isEmpty() && !value.isEmpty()) {
                        if (QuestionDAO.exists(key)) {
                            JOptionPane.showMessageDialog(null, "Já possui essa pergunta no banco de dados.");
                            return;
                        }
                        
                        addButtonAction(key, value);
                        model.addRow(new Object[]{key, value});
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, preencha ambos os campos.");
                    }
                }
            }
        });
        
        // Botão para remover a linha selecionada
        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String key = model.getValueAt(selectedRow, 0).toString();
                    
                    model.removeRow(selectedRow);
                    removeButtonAction(key);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para remover.");
                }
            }
        });
        
        JButton removeAllButton = new JButton("Remover tudo");
        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < map.size(); i++)
                    model.removeRow(0);
                
                for (Entry<String, String> entry : map.entrySet()) {
                    removeButtonAction(entry.getKey());
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void createAddPanel() {
    }
    
    public void addButtonAction(String key, String value) {
        QuestionDAO.insert(key, value);
    }
    
    public void removeButtonAction(String row) {
        QuestionDAO.delete(row);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminTela().setVisible(true);
            }
        });
    }
}
