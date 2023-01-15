package batalhanaval;

import java.util.Random;
import javax.swing.ImageIcon; // importação de icones com imagens
import javax.swing.JOptionPane; //importação de interfaces
import javax.swing.table.DefaultTableModel; //estrutura da tabela > mostrar

public class Main extends javax.swing.JFrame {

    int pecasUsuario = 0;
    int pecasComputador = 0;
    int[][] usuario = new int[8][8];
    int[][] computador = new int[8][8];
    ImageIcon barquinho = new ImageIcon(getClass().getResource("KleinesBoot.gif"));//1
    ImageIcon barcoDireita = new ImageIcon(getClass().getResource("RechtsBoot.gif"));//2
    ImageIcon barcoEsquerda = new ImageIcon(getClass().getResource("GelassenBoot.gif"));//3;
    ImageIcon navioDireita = new ImageIcon(getClass().getResource("RechtsSchiff.gif"));//4
    ImageIcon navioMeio = new ImageIcon(getClass().getResource("MediumSchiff.gif"));//5
    ImageIcon navioEsquerda = new ImageIcon(getClass().getResource("GelassenSchiff.gif"));//6
    ImageIcon vazio = new ImageIcon(getClass().getResource("Leer.gif"));
    ImageIcon acertoComputador = new ImageIcon(getClass().getResource("Rechts.gif"));

    public Main() {
        initComponents();
        construct();
    }

     public void jogadaUsuario() {
	 //variaveis que descobrem a posição que o usuario selecionou
        int y = jTableMaquina.getSelectedColumn();
        int x = jTableMaquina.getSelectedRow();
        switch (computador[x][y]) {
	//descobre se tem alguma peça no local que o usuario seleciounou
            case 0:
                jTableMaquina.setValueAt(vazio, x, y);
                break;
            case 1:
                jTableMaquina.setValueAt(barquinho, x, y);
                pecasComputador--;
                computador[x][y] = 0;
                break;
            case 2:
                jTableMaquina.setValueAt(barcoDireita, x, y);
                pecasComputador--;
                computador[x][y] = 0;
                break;
            case 3:
                jTableMaquina.setValueAt(barcoEsquerda, x, y);
                pecasComputador--;
                computador[x][y] = 0;
                break;
            case 4:
                jTableMaquina.setValueAt(navioDireita, x, y);
                pecasComputador--;
                computador[x][y] = 0;
                break;
            case 5:
                jTableMaquina.setValueAt(navioMeio, x, y);
                pecasComputador--;
                computador[x][y] = 0;
                break;
            case 6:
                jTableMaquina.setValueAt(navioEsquerda, x, y);
                pecasComputador--;
                computador[x][y] = 0;
                break;
        }
    }

    public void jogadaMaquina() {
        Random aleatorio = new Random();
		//gera uma posição aleatoria para o computador jogar
        int a = aleatorio.nextInt(8);
        int b = aleatorio.nextInt(8);
        switch (usuario[a][b]) {        
		//vê se tem alguma peça no lugar selecionado
            case 0:
                jTableUsuario.setValueAt(vazio, a, b);
                break;
            case 1:
                jTableUsuario.setValueAt(acertoComputador, a, b);
                pecasComputador--;
                break;
            case 2:
                jTableUsuario.setValueAt(acertoComputador, a, b);
                pecasComputador--;
                break;
            case 3:
                jTableUsuario.setValueAt(acertoComputador, a, b);
                pecasComputador--;
                break;
            case 4:
                jTableUsuario.setValueAt(acertoComputador, a, b);
                pecasComputador--;
                break;
            case 5:
                jTableUsuario.setValueAt(acertoComputador, a, b);
                pecasComputador--;
                break;
            case 6:
                jTableUsuario.setValueAt(acertoComputador, a, b);
                pecasComputador--;
                break;
        }
        if (pecasUsuario == 0) {        
		//se não sobrar nenhuma peça você perdeu
            JOptionPane.showMessageDialog(null, "a não você perdeu");
            this.dispose();
        }
    }

    public void construct() {

        Random aleatorio = new Random();
        //for que vai gerar 6 barcos diferentes
        for (int i = 0; i < 6; i++) {
            //randoms que vão dizer qual o tamanho do barco e a posição em que se encontram
            int tipo = aleatorio.nextInt(3); 
            int x = aleatorio.nextInt(8);
            int y = aleatorio.nextInt(8);
            //vê se é possível começar a construção a partir do lugar gerado
            if (usuario[x][y] != 0) {
                i--;//se não for retorna pra gerar novamente esses dados e posições
            } else {//se for começa a construir
                switch (tipo) {
                    case 0://barco pequeno adiciona ele na tabela e adiciona uma peça
                        usuario[x][y] = 1; //1 = barquinho pequeno
                        pecasUsuario++;
                        jTableUsuario.setValueAt(barquinho, x, y);
                        break;
                    case 1://barco médio 2 peças
                        if (y > 6) {//se a primeira posição for maior que 6, no canto da tela, adiciona pra esquerda o navio 
									// constroi da direita pra esquerda
                            if (usuario[x][y - 1] != 0 || usuario[x][y] != 0) {//verifica se pode ser colocado se não retorna para o inicio
                                i--;										
                            } else {//construção do navio
                                pecasUsuario += 2;
                                usuario[x][y] = 2; // valor atribuido ao barco da direita
                                jTableUsuario.setValueAt(barcoDireita, x, y);
                                usuario[x][y - 1] = 3; // valor atribuido ao barco da esquerda
                                jTableUsuario.setValueAt(barcoEsquerda, x, y - 1);
                            }
                        } else if (y >= 0) { //construindo da esquerda pra direita
                            if (usuario[x][y + 1] != 0 || usuario[x][y] != 0) {//verifica se pode ser colocado se não retorna para o inicio
                                i--;
                            } else {//construção do navio
                                pecasUsuario += 2;
                                usuario[x][y] = 3;
                                jTableUsuario.setValueAt(barcoEsquerda, x, y);
                                usuario[x][y + 1] = 2;
                                jTableUsuario.setValueAt(barcoDireita, x, y + 1);
                            }
                        }
                        break;
                    case 2: //barco grande
                        if (y > 6) {//se a primeira posição for maior que 6, no canto da tela, adiciona pra esquerda o navio 
                            if (usuario[x][y - 1] != 0 || usuario[x][y - 2] != 0 || usuario[x][y] != 0) {//verifica se pode ser colocado se não retorna para o inicio
                                i--;
                            } else {//construção do navio
                                pecasUsuario += 3;
                                usuario[x][y] = 4;
                                jTableUsuario.setValueAt(navioDireita, x, y);
                                usuario[x][y - 1] = 5;
                                jTableUsuario.setValueAt(navioMeio, x, y - 1);
                                usuario[x][y - 2] = 6;
                                jTableUsuario.setValueAt(navioEsquerda, x, y - 2);
                            }
                        } else if (y < 1) {//se a primeira posição for maior que 1, adiciona o navio a partir do meio
                            if (usuario[x][y + 1] != 0 || usuario[x][y + 2] != 0 || usuario[x][y] != 0) {//verifica se pode ser colocado se não retorna para o inicio
                                i--;
                            } else {//construção do navio
                                pecasUsuario += 3;
                                usuario[x][y] = 6;
                                jTableUsuario.setValueAt(navioEsquerda, x, y);
                                usuario[x][y + 1] = 5;
                                jTableUsuario.setValueAt(navioMeio, x, y + 1);
                                usuario[x][y + 2] = 4;
                                jTableUsuario.setValueAt(navioDireita, x, y + 2);
                            }
                        } else if (y > 1 && y < 6) {//se a primeira posição for menor que 1, no canto da tela, adiciona pra direita todo o navio
													//contrução no meio
                            if (usuario[x][y - 1] != 0 || usuario[x][y + 1] != 0 || usuario[x][y] != 0) {//verifica se pode ser colocado se não retorna para o inicio
                                i--;
                            } else {//construção do navio
                                pecasUsuario += 3;
                                usuario[x][y] = 5;
                                jTableUsuario.setValueAt(navioMeio, x, y);
                                usuario[x][y + 1] = 4;
                                jTableUsuario.setValueAt(navioDireita, x, y + 1);
                                usuario[x][y - 1] = 6;
                                jTableUsuario.setValueAt(navioEsquerda, x, y - 1);
                            }
                        }
                        break;
                }
            }
        }

        //construcao barcos computador //repete o mesmo processo do usuário
        for (int i = 0; i < 6; i++) {
            int tipo = aleatorio.nextInt(3);
            int x = aleatorio.nextInt(8);
            int y = aleatorio.nextInt(8);
            if (computador[x][y] != 0) {//verificacao barco
                i--;
            } else {//construcao
                switch (tipo) {
                    case 0:
                        computador[x][y] = 1;
                        pecasComputador++;
                        break;
                    case 1:
                        if (y > 6) {
                            if (computador[x][y - 1] != 0 || computador[x][y] != 0) {
                                i--;
                            } else {
                                pecasComputador += 2;
                                computador[x][y] = 2;
                                computador[x][y - 1] = 3;
                            }
                        } else if (y >= 0) {
                            if (computador[x][y + 1] != 0  || computador[x][y] != 0) {
                                i--;
                            } else {
                                pecasComputador += 2;
                                computador[x][y] = 3;
                                computador[x][y + 1] = 2;
                            }
                        }
                        break;
                    case 2:

                        if (y > 6) {
                            if (computador[x][y - 1] != 0 || computador[x][y - 2] != 0 || computador[x][y] == 0) {
                                i--;
                            } else {
                                pecasComputador += 3;
                                computador[x][y] = 4;
                                computador[x][y - 1] = 5;
                                computador[x][y - 2] = 6;
                            }
                        } else if (y < 1) {
                            if (computador[x][y + 1] != 0 || computador[x][y + 2] != 0 || computador[x][y] == 0) {
                                i--;
                            } else {
                                pecasComputador += 3;
                                computador[x][y] = 6;
                                computador[x][y + 1] = 5;
                                computador[x][y + 2] = 4;
                            }
                        } else if (y > 1 && y < 6) {
                            if (computador[x][y - 1] != 0 || computador[x][y + 1] != 0 || computador[x][y] == 0) {
                                i--;
                            } else {
                                pecasComputador += 3;
                                computador[x][y] = 5;
                                computador[x][y + 1] = 4;
                                computador[x][y - 1] = 6;
                            }
                        }
                        break;
                }
            }
        }
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print(computador[x][y]);
            }
            System.out.println("");
        }
        System.out.println(pecasComputador);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ImageIcon Wasser = new ImageIcon(getClass().getResource("Wasser.gif"));
        Object [][] rows = {
            {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
            {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
            {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
            {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
            {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
            {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
            {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
            {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser}
        };
        String [] columns ={ "", "", "", "", "", "", "", ""};
        DefaultTableModel model = new DefaultTableModel(rows, columns){
            @Override
            public Class <?> getColumnClass(int column){
                switch (column){case 0: return ImageIcon.class;
                    case 1: return ImageIcon.class;
                    case 2: return ImageIcon.class;
                    case 3: return ImageIcon.class;
                    case 4: return ImageIcon.class;
                    case 5: return ImageIcon.class;
                    case 6: return ImageIcon.class;
                    case 7: return ImageIcon.class;
                    default: return Object.class;}
            }};
            jTableUsuario = new javax.swing.JTable(model);
            jScrollPane2 = new javax.swing.JScrollPane();
            Object [][] linhas = {
                {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
                {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
                {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
                {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
                {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
                {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
                {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser},
                {Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser, Wasser}
            };
            String [] colunas ={ "", "", "", "", "", "", "", ""};
            DefaultTableModel modelu = new DefaultTableModel(linhas, colunas){
                @Override
                public Class <?> getColumnClass(int column){
                    switch (column){case 0: return ImageIcon.class;
                        case 1: return ImageIcon.class;
                        case 2: return ImageIcon.class;
                        case 3: return ImageIcon.class;
                        case 4: return ImageIcon.class;
                        case 5: return ImageIcon.class;
                        case 6: return ImageIcon.class;
                        case 7: return ImageIcon.class;
                        default: return Object.class;}
                }};
                jTableMaquina = new javax.swing.JTable(modelu);
                jLabel1 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                /*
                jTableUsuario.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null}
                    },
                    new String [] {
                        "", "", "", "", "", "", "", ""
                    }
                ));
                */
                jTableUsuario.setEnabled(false);
                jTableUsuario.setRowHeight(50);
                jScrollPane1.setViewportView(jTableUsuario);

                /*
                jTableMaquina.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null}
                    },
                    new String [] {
                        "", "", "", "", "", "", "", ""
                    }
                ));
                */
                jTableMaquina.setColumnSelectionAllowed(false);
                jTableMaquina.setRowHeight(50);
                jTableMaquina.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jTableMaquinaMouseClicked(evt);
                    }
                });
                jScrollPane2.setViewportView(jTableMaquina);

                jLabel1.setFont(new java.awt.Font("Thinking Of Betty", 0, 24)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(0, 102, 204));
                jLabel1.setText("Batalha Naval");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(419, 419, 419))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void jTableMaquinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMaquinaMouseClicked
        jogadaUsuario();
        if (pecasComputador == 0) { //se após o calculo não sobrar nenhuma peça você ganhou
            JOptionPane.showMessageDialog(null, "parabens você ganhou");
            this.dispose();
        } else {
            jogadaMaquina();
        }
    }//GEN-LAST:event_jTableMaquinaMouseClicked

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTableMaquina;
    public javax.swing.JTable jTableUsuario;
    // End of variables declaration//GEN-END:variables
}
