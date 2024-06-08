/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.telas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import login.db.QuestionDAO;
import login.db.UsuarioDAO;
import login.modelo.Question;
import login.modelo.Usuario;

/**
 *
 * @author schix
 */
public class AdminTela extends javax.swing.JFrame {

    String addDialogHeader = "Exemplo de enunciado\nA - Exemplo de Resposta\nB - Exemplo de Resposta";
    String addDialogAnswer = "A";

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminTela().setVisible(true);
            }
        });
    }

    public AdminTela() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoginTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Configurando a janela principal
        setTitle("Painel Admin");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Perguntas", questionsScreen());
        tabbedPane.addTab("Recordes", highscoreScreen());

        setContentPane(tabbedPane);
    }

    public JPanel questionsScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel(new Object[] { "Pergunta", "Resposta" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        List<Question> questions = QuestionDAO.getQuestions();

        for (Question question : questions) {
            try {
                question.validated();
            } catch (Exception e) {
                question = new Question(question.getId(), "(INVÁLIDA) " + question.getHeader(), question.getOptions(),
                        question.getCorrectAnswer());
            }
            model.addRow(new Object[] { question.toRawText(), question.getCorrectOption() });
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddQuestionScreen(model, questions);
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
                    QuestionDAO.delete(questions.get(selectedRow).getId());
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para remover.");
                }
            }
        });

        JButton removeAllButton = new JButton("Remover tudo");
        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < questions.size(); i++)
                    model.removeRow(0);

                for (Question question : questions) {
                    QuestionDAO.delete(question.getId());
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    public JPanel highscoreScreen() {
        DefaultTableModel model = new DefaultTableModel(new Object[] { "Aluno", "Recorde" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        List<Usuario> usuarios = UsuarioDAO.getAll();

        for (Usuario usuario : usuarios) {
            model.addRow(new Object[] { usuario.getLogin(), usuario.getRecorde() });
        }

        JTable table = new JTable(model);

        JPanel buttonPanel = new JPanel();

        // Botão para remover a linha selecionada
        JButton resetButton = new JButton("Resetar");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.setValueAt(0, selectedRow, 1);
                    UsuarioDAO.setRecorde(usuarios.get(selectedRow).getCodigo(), 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para resetar.");
                }
            }
        });

        JButton resetAllButton = new JButton("Resetar tudo");
        resetAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < usuarios.size(); i++) {
                    model.setValueAt(0, i, 1);
                    UsuarioDAO.setRecorde(usuarios.get(i).getCodigo(), 0);
                }
            }
        });

        buttonPanel.add(resetButton);
        buttonPanel.add(resetAllButton);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel();

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    public void showAddQuestionScreen(DefaultTableModel model, List<Question> questions) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        var border = BorderFactory.createEmptyBorder(20, 20, 20, 20);

        JTextArea keyField = new JTextArea(5, 50);
        keyField.setBorder(border);

        JLabel questionLabel = new JLabel("Pergunta:");
        questionLabel.setBorder(border);

        questionLabel.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(questionLabel);
        panel.add(keyField);

        JTextField valueField = new JTextField(50);
        valueField.setBorder(border);

        JLabel answerLabel = new JLabel("Resposta:");
        answerLabel.setBorder(border);

        keyField.setText(addDialogHeader);
        valueField.setText(addDialogAnswer);

        answerLabel.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(answerLabel);
        panel.add(valueField);

        JPanel buttonsPanel = new JPanel();
        JButton add = new JButton("Adicionar");
        JButton cancel = new JButton("Cancelar");

        buttonsPanel.add(add);
        buttonsPanel.add(cancel);
        buttonsPanel.setBorder(border);

        panel.add(buttonsPanel);

        panel.setBorder(border);

        JDialog dialog = new JDialog(this, true);

        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addDialogHeader = keyField.getText();
                addDialogAnswer = valueField.getText();

                if (!addDialogHeader.isEmpty() && !addDialogAnswer.isEmpty()) {
                    if (QuestionDAO.existsByRawText(addDialogHeader)) {
                        JOptionPane.showMessageDialog(null, "Já possui essa pergunta no banco de dados.");
                        return;
                    }

                    try {
                        questions.add(QuestionDAO
                                .insert(new Question(0, addDialogHeader, addDialogAnswer).validated()));
                        model.addRow(new Object[] { addDialogHeader, addDialogAnswer });
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dialog, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha ambos os campos.");
                }
                dialog.dispose();
            }

        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.setContentPane(panel);
        dialog.setTitle("Adicionar pergunta e resposta");
        dialog.setResizable(true);
        dialog.setSize(650, 450);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
