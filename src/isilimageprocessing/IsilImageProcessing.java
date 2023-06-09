/*
 * TestCImage2.java
 *
 * Created on 10 juillet 2007, 16:50
 */

package isilimageprocessing;

import CImage.*;
import CImage.Exceptions.*;
import CImage.Observers.*;
import CImage.Observers.Events.*;
import Etape5.Etape5;
import ImageProcessing.Complexe.MatriceComplexe;
import ImageProcessing.Contours.ContoursNonLineaire;
import ImageProcessing.Contours.ContoursLineaire;
import ImageProcessing.Fourier.Fourier;
import ImageProcessing.Histogramme.Histogramme;

import ImageProcessing.Seuillage.Seuillage;


import ImageProcessing.NonLineaire.MorphoComplexe;
import ImageProcessing.NonLineaire.MorphoElementaire;
import ImageProcessing.Lineaire.FiltrageLinaireGlobal;
import ImageProcessing.Lineaire.FiltrageLineaireLocal;
import ImageProcessing.Utils;

import isilimageprocessing.Dialogues.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author  HP_Propriétaire
 */
public class IsilImageProcessing extends javax.swing.JFrame implements ClicListener,SelectLigneListener,SelectRectListener,SelectRectFillListener,SelectCercleListener,SelectCercleFillListener
{
    private CImageRGB imageRGB;
    private CImageNG  imageNG;

    private JLabelBeanCImage observer;
    private Color couleurPinceauRGB;
    private int   couleurPinceauNG;

    /** Creates new form TestCImage2 */
    public IsilImageProcessing()
    {
        setSize(1000,1000);
        setMinimumSize(new Dimension(1000,1000));
        initComponents();

        imageRGB = null;
        imageNG  = null;

        observer = new JLabelBeanCImage();
        observer.addClicListener(this);
        observer.addSelectLigneListener(this);
        observer.addSelectRectListener(this);
        observer.addSelectRectFillListener(this);
        observer.addSelectCercleListener(this);
        observer.addSelectCercleFillListener(this);
        observer.setMode(JLabelBeanCImage.INACTIF);
        jScrollPane.setViewportView(observer);

        jMenuDessiner.setEnabled(false);
        jMenuFourier.setEnabled(false);
        jMenuHistogramme.setEnabled(false);

        //
        jMenuFiltrage.setEnabled(false);
        jMenuContours.setEnabled(false);

        couleurPinceauRGB = Color.BLACK;
        couleurPinceauNG = 0;

        /***************************/
        //Menu Histogramme
        /***************************/
        // Les bases sont d�j� faites plus haut

        jMenuHistogrammeAfficherParamImage.setText("Afficher les parametres image");
        jMenuHistogrammeTraitementLineaire.setText("traitement lineaire avec saturation");
        jMenuHistogrammeTraitementGamma.setText("traitement non-lineaire Gamma");
        jMenuHistogrammeTraitementNegatif.setText("traitement Negatif");
        jMenuHistogrammeTraitementEgalisation.setText("traitement Egalisation");
        jMenuHistogrammeAfficherParamImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHistogrammeAfficherParamImageActionPerformed(evt);
            }
        });

        jMenuHistogrammeTraitementLineaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHistogrammeTraitementLineaireActionPerformed(evt);
            }
        });
        jMenuHistogrammeTraitementGamma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHistogrammeTraitementGammaActionPerformed(evt);
            }
        });

        jMenuHistogrammeTraitementNegatif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHistogrammeTraitementNegatifActionPerformed(evt);
            }
        });

        jMenuHistogrammeTraitementEgalisation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHistogrammeTraitementEgalisationActionPerformed(evt);
            }
        });

        jMenuHistogramme.add(jMenuHistogrammeAfficherParamImage);
        jMenuHistogramme.add(jMenuHistogrammeTraitementLineaire);
        jMenuHistogramme.add(jMenuHistogrammeTraitementGamma);
        jMenuHistogramme.add(jMenuHistogrammeTraitementNegatif);
        jMenuHistogramme.add(jMenuHistogrammeTraitementEgalisation);


        /***************************/
        /***************************/



    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroupDessiner = new javax.swing.ButtonGroup();
        jScrollPane = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuImage = new javax.swing.JMenu();
        jMenuNouvelle = new javax.swing.JMenu();
        jMenuItemNouvelleRGB = new javax.swing.JMenuItem();
        jMenuItemNouvelleNG = new javax.swing.JMenuItem();
        jMenuOuvrir = new javax.swing.JMenu();
        jMenuItemOuvrirRGB = new javax.swing.JMenuItem();
        jMenuItemOuvrirNG = new javax.swing.JMenuItem();
        jMenuItemEnregistrerSous = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuQuitter = new javax.swing.JMenuItem();
        jMenuDessiner = new javax.swing.JMenu();
        jMenuItemCouleurPinceau = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jCheckBoxMenuItemDessinerPixel = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerLigne = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerRectangle = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerRectanglePlein = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerCercle = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerCerclePlein = new javax.swing.JCheckBoxMenuItem();
        jMenuFourier = new javax.swing.JMenu();
        jMenuFourierAfficher = new javax.swing.JMenu();
        jMenuItemFourierAfficherModule = new javax.swing.JMenuItem();
        jMenuItemFourierAfficherPhase = new javax.swing.JMenuItem();
        jMenuItemFourierAfficherPartieReelle = new javax.swing.JMenuItem();
        jMenuItemFourierAfficherPartieImaginaire = new javax.swing.JMenuItem();
        jMenuHistogramme = new javax.swing.JMenu();
        jMenuHistogrammeAfficher = new javax.swing.JMenuItem();
        jMenuHistogrammeAfficherParamImage = new javax.swing.JMenuItem();
        jMenuHistogrammeTraitementLineaire = new javax.swing.JMenuItem();
        jMenuHistogrammeTraitementGamma = new javax.swing.JMenuItem();
        jMenuHistogrammeTraitementNegatif = new javax.swing.JMenuItem();
        jMenuHistogrammeTraitementEgalisation = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TestCImage3");

        jMenuImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Net 13_p1.jpg")));
        jMenuImage.setText("Image");
        jMenuNouvelle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/File 65_p3.jpg")));
        jMenuNouvelle.setText("Nouvelle");
        jMenuItemNouvelleRGB.setText("Image RGB");
        jMenuItemNouvelleRGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNouvelleRGBActionPerformed(evt);
            }
        });

        jMenuNouvelle.add(jMenuItemNouvelleRGB);

        jMenuItemNouvelleNG.setText("Image NG");
        jMenuItemNouvelleNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNouvelleNGActionPerformed(evt);
            }
        });

        jMenuNouvelle.add(jMenuItemNouvelleNG);

        jMenuImage.add(jMenuNouvelle);

        jMenuOuvrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Folder 036_p3.jpg")));
        jMenuOuvrir.setText("Ouvrir");
        jMenuItemOuvrirRGB.setText("Image RGB");
        jMenuItemOuvrirRGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOuvrirRGBActionPerformed(evt);
            }
        });

        jMenuOuvrir.add(jMenuItemOuvrirRGB);

        jMenuItemOuvrirNG.setText("Image NG");
        jMenuItemOuvrirNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOuvrirNGActionPerformed(evt);
            }
        });

        jMenuOuvrir.add(jMenuItemOuvrirNG);

        jMenuImage.add(jMenuOuvrir);

        jMenuItemEnregistrerSous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/DD 27_p3.jpg")));
        jMenuItemEnregistrerSous.setText("Enregistrer sous...");
        jMenuItemEnregistrerSous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEnregistrerSousActionPerformed(evt);
            }
        });

        jMenuImage.add(jMenuItemEnregistrerSous);

        jMenuImage.add(jSeparator1);

        jMenuQuitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/CP 59_p3.jpg")));
        jMenuQuitter.setText("Quitter");
        jMenuQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQuitterActionPerformed(evt);
            }
        });

        jMenuImage.add(jMenuQuitter);

        jMenuBar1.add(jMenuImage);

        jMenuDessiner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Display 28_p1.jpg")));
        jMenuDessiner.setText("Dessiner");
        jMenuItemCouleurPinceau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Display 14_p3.jpg")));
        jMenuItemCouleurPinceau.setText("Couleur");
        jMenuItemCouleurPinceau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleurPinceauActionPerformed(evt);
            }
        });

        jMenuDessiner.add(jMenuItemCouleurPinceau);

        jMenuDessiner.add(jSeparator2);

        jCheckBoxMenuItemDessinerPixel.setText("Pixel");
        jCheckBoxMenuItemDessinerPixel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerPixelActionPerformed(evt);
            }
        });

        jMenuDessiner.add(jCheckBoxMenuItemDessinerPixel);

        jCheckBoxMenuItemDessinerLigne.setText("Ligne");
        jCheckBoxMenuItemDessinerLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerLigneActionPerformed(evt);
            }
        });

        jMenuDessiner.add(jCheckBoxMenuItemDessinerLigne);

        jCheckBoxMenuItemDessinerRectangle.setText("Rectangle");
        jCheckBoxMenuItemDessinerRectangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerRectangleActionPerformed(evt);
            }
        });

        jMenuDessiner.add(jCheckBoxMenuItemDessinerRectangle);

        jCheckBoxMenuItemDessinerRectanglePlein.setText("Rectangle plein");
        jCheckBoxMenuItemDessinerRectanglePlein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerRectanglePleinActionPerformed(evt);
            }
        });

        jMenuDessiner.add(jCheckBoxMenuItemDessinerRectanglePlein);

        jCheckBoxMenuItemDessinerCercle.setText("Cercle");
        jCheckBoxMenuItemDessinerCercle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerCercleActionPerformed(evt);
            }
        });

        jMenuDessiner.add(jCheckBoxMenuItemDessinerCercle);

        jCheckBoxMenuItemDessinerCerclePlein.setText("Cercle plein");
        jCheckBoxMenuItemDessinerCerclePlein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerCerclePleinActionPerformed(evt);
            }
        });

        jMenuDessiner.add(jCheckBoxMenuItemDessinerCerclePlein);

        jMenuBar1.add(jMenuDessiner);

        jMenuFourier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/CP 51_p1.jpg")));
        jMenuFourier.setText("Fourier");
        jMenuFourierAfficher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/CP 51_p3.jpg")));
        jMenuFourierAfficher.setText("Afficher");
        jMenuItemFourierAfficherModule.setText("Module");
        jMenuItemFourierAfficherModule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherModuleActionPerformed(evt);
            }
        });

        jMenuFourierAfficher.add(jMenuItemFourierAfficherModule);

        jMenuItemFourierAfficherPhase.setText("Phase");
        jMenuItemFourierAfficherPhase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherPhaseActionPerformed(evt);
            }
        });

        jMenuFourierAfficher.add(jMenuItemFourierAfficherPhase);

        jMenuItemFourierAfficherPartieReelle.setText("Partie Reelle");
        jMenuItemFourierAfficherPartieReelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherPartieReelleActionPerformed(evt);
            }
        });

        jMenuFourierAfficher.add(jMenuItemFourierAfficherPartieReelle);

        jMenuItemFourierAfficherPartieImaginaire.setText("Partie Imaginaire");
        jMenuItemFourierAfficherPartieImaginaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherPartieImaginaireActionPerformed(evt);
            }
        });

        jMenuFourierAfficher.add(jMenuItemFourierAfficherPartieImaginaire);

        jMenuFourier.add(jMenuFourierAfficher);

        jMenuBar1.add(jMenuFourier);

        jMenuHistogramme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/report_48_hot.jpg")));
        jMenuHistogramme.setText("Histogramme");
        jMenuHistogrammeAfficher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/report_32_hot.jpg")));
        jMenuHistogrammeAfficher.setText("Afficher");
        jMenuHistogrammeAfficher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHistogrammeAfficherActionPerformed(evt);
            }
        });

        jMenuHistogramme.add(jMenuHistogrammeAfficher);

        jMenuBar1.add(jMenuHistogramme);

        //ICI
        jMenuFiltrage = new javax.swing.JMenu();
        jMenuFiltrage.setText("Filtrage lineaire");
        jMenuBar1.add(jMenuFiltrage);
        jMenuFiltrageGlobal = new javax.swing.JMenu();
        jMenuFiltrageGlobal.setText("Global");
        jMenuFiltrage.add(jMenuFiltrageGlobal);
        jMenuFiltrageGlobalBasIdeal = new javax.swing.JMenuItem();
        jMenuFiltrageGlobalBasIdeal.setText("Passe-bas ideal");
        jMenuFiltrageGlobalBasIdeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFiltrageGlobalBasIdealActionPerformed(evt);
            }
        });
        jMenuFiltrageGlobalHautIdeal = new javax.swing.JMenuItem();
        jMenuFiltrageGlobalHautIdeal.setText("Passe-Haut ideal");
        jMenuFiltrageGlobalHautIdeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFiltrageGlobalHautIdealActionPerformed(evt);
            }
        });
        jMenuFiltrageGlobalBasButterworth = new javax.swing.JMenuItem();
        jMenuFiltrageGlobalBasButterworth.setText("Passe-Bas Butterworth");
        jMenuFiltrageGlobalBasButterworth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFiltrageGlobalBasButterworthActionPerformed(evt);
            }
        });
        jMenuFiltrageGlobalHautButterworth = new javax.swing.JMenuItem();
        jMenuFiltrageGlobalHautButterworth.setText("Passe-Haut Butterworth");
        jMenuFiltrageGlobalHautButterworth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFiltrageGlobalHautButterworthActionPerformed(evt);
            }
        });

        jMenuFiltrageGlobal.add(jMenuFiltrageGlobalBasIdeal);
        jMenuFiltrageGlobal.add(jMenuFiltrageGlobalHautIdeal);
        jMenuFiltrageGlobal.add(jMenuFiltrageGlobalBasButterworth);
        jMenuFiltrageGlobal.add(jMenuFiltrageGlobalHautButterworth);


        /***************************/
        //Traitements non lineaire
        /***************************/
        jMenuTraitementNonLineaire = new javax.swing.JMenu();
        jMenuTraitementNonLineaire.setText("Traitement non lineaire");
        jMenuBar1.add(jMenuTraitementNonLineaire);

        jMenuTraitementElementaire = new javax.swing.JMenu();
        jMenuTraitementElementaire.setText("Elementaire");
        jMenuTraitementNonLineaire.add(jMenuTraitementElementaire);

        jMenuTraitementComplexe = new javax.swing.JMenu();
        jMenuTraitementComplexe.setText("Complexe");
        jMenuTraitementNonLineaire.add(jMenuTraitementComplexe);



        /* Item du sous-menu Elementaire */

        jMenuTraitementElementaireErosion = new javax.swing.JMenuItem();
        jMenuTraitementElementaireErosion.setText("Erosion");
        jMenuTraitementElementaireErosion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTraitementElementaireErosion(evt);
            }
        });

        jMenuTraitementElementaireDilatation = new javax.swing.JMenuItem();
        jMenuTraitementElementaireDilatation.setText("Dilatation");
        jMenuTraitementElementaireDilatation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTraitementElementaireDilatation(evt);
            }
        });

        jMenuTraitementElementaireOuverture = new javax.swing.JMenuItem();
        jMenuTraitementElementaireOuverture.setText("Ouverture");
        jMenuTraitementElementaireOuverture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTraitementElementaireOuverture(evt);
            }
        });

        jMenuTraitementElementaireFermeture = new javax.swing.JMenuItem();
        jMenuTraitementElementaireFermeture.setText("Fermeture");
        jMenuTraitementElementaireFermeture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTraitementElementaireFermeture(evt);
            }
        });

        jMenuTraitementElementaire.add(jMenuTraitementElementaireErosion);
        jMenuTraitementElementaire.add(jMenuTraitementElementaireDilatation);
        jMenuTraitementElementaire.add(jMenuTraitementElementaireOuverture);
        jMenuTraitementElementaire.add(jMenuTraitementElementaireFermeture);

        /* Item du sous-menu Complexe */

        jMenuTraitementComplexeDilatationGeodesique = new javax.swing.JMenuItem();
        jMenuTraitementComplexeDilatationGeodesique.setText("Dilatation Geodesique");
        jMenuTraitementComplexeDilatationGeodesique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTraitementComplexeDilatationGeodesique(evt);
            }
        });

        jMenuTraitementComplexeReconstructionGeodesique = new javax.swing.JMenuItem();
        jMenuTraitementComplexeReconstructionGeodesique.setText("Reconstruction geodesique");
        jMenuTraitementComplexeReconstructionGeodesique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTraitementComplexeReconstructionGeodesique(evt);
            }
        });

        jMenuTraitementComplexeFiltreMedian = new javax.swing.JMenuItem();
        jMenuTraitementComplexeFiltreMedian.setText("Filtre Median");
        jMenuTraitementComplexeFiltreMedian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTraitementComplexeFiltreMedian(evt);
            }
        });

        jMenuTraitementComplexe.add(jMenuTraitementComplexeDilatationGeodesique);
        jMenuTraitementComplexe.add(jMenuTraitementComplexeReconstructionGeodesique);
        jMenuTraitementComplexe.add(jMenuTraitementComplexeFiltreMedian);



        /***************************/
        //Contours
        /***************************/
        jMenuContours = new javax.swing.JMenu();
        jMenuContours.setText("Contours");
        jMenuBar1.add(jMenuContours);

        /* Item du sous-menu lineaire */
        jMenuContoursLineaire = new javax.swing.JMenu();
        jMenuContoursLineaire.setText("Lineaire");
        jMenuContours .add(jMenuContoursLineaire);
        jMenuContoursLineairePrewitt = new javax.swing.JMenuItem();
        jMenuContoursLineairePrewitt.setText("Prewitt");
        jMenuContoursLineairePrewitt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuContoursLineairePrewittActionPerformed(evt);
            }
        });
        jMenuContoursLineaireSobel = new javax.swing.JMenuItem();
        jMenuContoursLineaireSobel.setText("Sobel");
        jMenuContoursLineaireSobel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuContoursLineaireSobelActionPerformed(evt);
            }
        });
        jMenuContoursLineaireLaplacien4 = new javax.swing.JMenuItem();
        jMenuContoursLineaireLaplacien4.setText("Le Placien 4");
        jMenuContoursLineaireLaplacien4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuContoursLineaireLaplacien4ActionPerformed(evt);
            }
        });
        jMenuContoursLineaireLaplacien8 = new javax.swing.JMenuItem();
        jMenuContoursLineaireLaplacien8.setText("Le Placien 8");
        jMenuContoursLineaireLaplacien8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuContoursLineaireLaplacien8ActionPerformed(evt);
            }
        });


        /* Item du sous-menu Non-lineaire */
        jMenuContoursNonLineaire = new javax.swing.JMenu();
        jMenuContoursNonLineaire.setText("Non-lineaire");
        jMenuContours.add(jMenuContoursNonLineaire);

        jMenuContoursNonLineairegradientErosion = new javax.swing.JMenuItem();
        jMenuContoursNonLineairegradientErosion.setText("Gradient Erosion");
        jMenuContoursNonLineairegradientErosion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContoursNonLineairegradientErosion(evt);
            }
        });

        jMenuContoursNonLineairegradientDilatation = new javax.swing.JMenuItem();
        jMenuContoursNonLineairegradientDilatation.setText("Gradient Dilatation");
        jMenuContoursNonLineairegradientDilatation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContoursNonLineairegradientDilatation(evt);
            }
        });

        jMenuContoursNonLineairegradientBeucher = new javax.swing.JMenuItem();
        jMenuContoursNonLineairegradientBeucher.setText("Gradient Beucher");
        jMenuContoursNonLineairegradientBeucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContoursNonLineairegradientBeucher(evt);
            }
        });

        jMenuContoursNonLineairelaplacienNonLineaire = new javax.swing.JMenuItem();
        jMenuContoursNonLineairelaplacienNonLineaire.setText("laplacienNonLineaire");
        jMenuContoursNonLineairelaplacienNonLineaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContoursNonLineairelaplacienNonLineaire(evt);
            }
        });
        jMenuContoursNonLineaire.add(jMenuContoursNonLineairegradientErosion);
        jMenuContoursNonLineaire.add(jMenuContoursNonLineairegradientDilatation);
        jMenuContoursNonLineaire.add(jMenuContoursNonLineairegradientBeucher);
        jMenuContoursNonLineaire.add(jMenuContoursNonLineairelaplacienNonLineaire);
        jMenuContoursLineaire.add(jMenuContoursLineairePrewitt);
        jMenuContoursLineaire.add(jMenuContoursLineaireSobel);
        jMenuContoursLineaire.add(jMenuContoursLineaireLaplacien4);
        jMenuContoursLineaire.add(jMenuContoursLineaireLaplacien8);


        /***************************/
        //Menu de tests
        /***************************/

        jMenuMenuDeTest = new javax.swing.JMenu();
        jMenuMenuDeTest.setText("Menu de test");
        jMenuBar1.add(jMenuMenuDeTest);

        jMenuCreerImageNGEnCode = new javax.swing.JMenuItem();
        jMenuCreerImageNGEnCode.setText("Créer image NG en code");
        jMenuCreerImageNGEnCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCreerImageNGEnCode(evt);
            }
        });

        jMenuMenuDeTest.add(jMenuCreerImageNGEnCode);


        /***************************/
        /***************************/


        /******************
            Menu Seuillage
        *****************/


        //Menu Seuillage
        jMenuSeuillage = new javax.swing.JMenu();
        jMenuSeuillage.setText("Seuillage");
        jMenuBar1.add(jMenuSeuillage);

        //Items du menu Seuillage
        jMenuSeuillageSeuillageSimple = new javax.swing.JMenuItem();
        jMenuSeuillageSeuillageSimple.setText("Seuillage simple");
        jMenuSeuillageSeuillageSimple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSeuillageSeuillageSimpleActionPerformed(evt);
            }
        });

        jMenuSeuillageSeuillageDouble = new javax.swing.JMenuItem();
        jMenuSeuillageSeuillageDouble.setText("Seuillage double");
        jMenuSeuillageSeuillageDouble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSeuillageSeuillageDoubleActionPerformed(evt);
            }
        });

        jMenuSeuillageSeuillageAutomatique = new javax.swing.JMenuItem();
        jMenuSeuillageSeuillageAutomatique.setText("Seuillage Automatique");
        jMenuSeuillageSeuillageAutomatique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSeuillageSeuillageAutomatiqueActionPerformed(evt);
            }
        });

        //Ajouts des items au menu Seuillage
        jMenuSeuillage.add(jMenuSeuillageSeuillageSimple);
        jMenuSeuillage.add(jMenuSeuillageSeuillageDouble);
        jMenuSeuillage.add(jMenuSeuillageSeuillageAutomatique);

        /******************/
        /******************/

        //
        jMenuFiltrageLocal = new javax.swing.JMenu();
        jMenuFiltrageLocal.setText("Local");
        jMenuFiltrage.add(jMenuFiltrageLocal);
        jMenuFiltrageMasqueConvolution = new javax.swing.JMenuItem();
        jMenuFiltrageMasqueConvolution.setText("Masque Convolution");
        jMenuFiltrageMasqueConvolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFiltrageLocalConvolutionActionPerformed(evt);
            }
        });
        jMenuFiltrageMoyenneur = new javax.swing.JMenuItem();
        jMenuFiltrageMoyenneur.setText("Moyenneur");
        jMenuFiltrageMoyenneur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFiltrageLocalMoyenneurActionPerformed(evt);
            }
        });

        jMenuFiltrageLocal.add(jMenuFiltrageMasqueConvolution);
        jMenuFiltrageLocal.add(jMenuFiltrageMoyenneur);

        /***************************/
        //Etape 5
        /***************************/
        jMenuEtape5 = new javax.swing.JMenu();
        jMenuEtape5.setText("Etape 5");
        jMenuBar1.add(jMenuEtape5);

        jMenuEtape5_1 = new javax.swing.JMenuItem();
        jMenuEtape5_2 = new javax.swing.JMenuItem();
        jMenuEtape5_3 = new javax.swing.JMenuItem();
        jMenuEtape5_4 = new javax.swing.JMenuItem();
        jMenuEtape5_5 = new javax.swing.JMenuItem();
        jMenuEtape5_6 = new javax.swing.JMenuItem();
        jMenuEtape5_7 = new javax.swing.JMenuItem();

        jMenuEtape5_1.setText("Etape 5.1 : LenaBruit");
        jMenuEtape5_2.setText("Etape 5.2 : LenaAEgaliser");
        jMenuEtape5_3.setText("Etape 5.3 : petitPois");
        jMenuEtape5_4.setText("Etape 5.4 : balanes");
        jMenuEtape5_5.setText("Etape 5.5 : tools");
        jMenuEtape5_6.setText("Etape 5.6 : vaisseaux");
        jMenuEtape5_7.setText("Etape 5.7 : Tartines");

        jMenuEtape5_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEtape5_1ActionPerformed(evt);
            }
        });
        jMenuEtape5_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEtape5_2ActionPerformed(evt);
            }
        });
        jMenuEtape5_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEtape5_3ActionPerformed(evt);
            }
        });
        jMenuEtape5_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEtape5_4ActionPerformed(evt);
            }
        });
        jMenuEtape5_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEtape5_5ActionPerformed(evt);
            }
        });
        jMenuEtape5_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEtape5_6ActionPerformed(evt);
            }
        });
        jMenuEtape5_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEtape5_7ActionPerformed(evt);
            }
        });


        jMenuEtape5.add(jMenuEtape5_1);
        jMenuEtape5.add(jMenuEtape5_2);
        jMenuEtape5.add(jMenuEtape5_3);
        jMenuEtape5.add(jMenuEtape5_4);
        jMenuEtape5.add(jMenuEtape5_5);
        jMenuEtape5.add(jMenuEtape5_6);
        jMenuEtape5.add(jMenuEtape5_7);

        /******************/
        /******************/

        //
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                .addContainerGap())
        );
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-500)/2, (screenSize.height-400)/2, 500, 400);
    }// </editor-fold>//GEN-END:initComponents


    private void jMenuEtape5_1ActionPerformed(java.awt.event.ActionEvent evt) {

        try {


            if (imageRGB != null) {
                int[][] MatriceRed = new int[imageRGB.getLargeur()][imageRGB.getHauteur()];
                int[][] MatriceGreen = new int[imageRGB.getLargeur()][imageRGB.getHauteur()];
                int[][] MatriceBlue = new int[imageRGB.getLargeur()][imageRGB.getHauteur()];


                imageRGB.getMatricesRGB(MatriceRed, MatriceGreen, MatriceBlue);

                System.out.println("Debut du calcul des 3 matrices");
                int[][] MatriceRedSortie = Etape5.Etape5_1_LenaBruit(MatriceRed);
                int[][] MatriceGreenSortie = Etape5.Etape5_1_LenaBruit(MatriceGreen);
                int[][] MatriceBlueSortie = Etape5.Etape5_1_LenaBruit(MatriceBlue);
                System.out.println("Fin du calcul des 3 matrices");

                imageRGB.setMatricesRGB(MatriceRedSortie, MatriceGreenSortie, MatriceBlueSortie);

            }
            else
            {
                int[][] MatriceNG = imageNG.getMatrice();
                int[][] MatriceNGSortie = Etape5.Etape5_1_LenaBruit(MatriceNG);
                imageNG.setMatrice(MatriceNGSortie);
            }
        }
        catch (CImageRGBException e) {

        } catch (CImageNGException e) {

        }

    }
    private void jMenuEtape5_2ActionPerformed(java.awt.event.ActionEvent evt)
    {


    }
    private void jMenuEtape5_3ActionPerformed(java.awt.event.ActionEvent evt)
    {
        try {


            if (imageRGB != null) {
                int[][] MatriceRed = new int[imageRGB.getLargeur()][imageRGB.getHauteur()];
                int[][] MatriceGreen = new int[imageRGB.getLargeur()][imageRGB.getHauteur()];
                int[][] MatriceBlue = new int[imageRGB.getLargeur()][imageRGB.getHauteur()];

                imageRGB.getMatricesRGB(MatriceRed, MatriceGreen, MatriceBlue);

                System.out.println("Debut du calcul des 3 matrices");

              /*  int[][] MatriceRedSortie = Etape5.Etape5_3_PetitsPois(MatriceRed); // tous sauf rouge
                int[][] MatriceGreenSortie = Etape5.Etape5_3_PetitsPois(MatriceGreen); // tous sauf vert
                int[][] MatriceBlueSortie = Etape5.Etape5_3_PetitsPois(MatriceBlue); // tous sauf bleu
*/
                System.out.println("Fin du calcul des 3 matrices");
                int[][] rouge = new int[MatriceRed.length][MatriceRed[0].length];
                int[][] bleu = new int[MatriceRed.length][MatriceRed[0].length];

                for (int i = 0; i < rouge.length; i++) {
                    for (int j = 0; j < rouge[0].length; j++) {
                        // bleu => rouge ou vert
                        // vert => rouge ou bleu
                        // rouge => vert ou bleu
                        if (MatriceGreen[i][j] == MatriceBlue[i][j]) {
                            rouge[i][j] = MatriceGreen[i][j];
                        } else {
                            rouge[i][j] = 255;
                        }
                        if (MatriceRed[i][j] == MatriceGreen[i][j]) {
                            bleu[i][j] = MatriceRed[i][j];
                        } else {
                            bleu[i][j] = 255;
                        }
                    }
                }

                JDialogAfficheMatriceDouble rougeDialog = new JDialogAfficheMatriceDouble(this, true, Utils.intToDouble(rouge), "Que du rouge");
                rougeDialog.setVisible(true);
                JDialogAfficheMatriceDouble bleuDialog = new JDialogAfficheMatriceDouble(this, true, Utils.intToDouble(bleu), "Que du Bleu");
                bleuDialog.setVisible(true);

            }
            else
            {
                System.out.println("Nop");
            }
        }
        catch (CImageRGBException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    private void jMenuEtape5_4ActionPerformed(java.awt.event.ActionEvent evt)
    {
        try {
            if (imageRGB != null) {
                imageNG = imageRGB.getCImageNG();
            }
            int[][] f_int = imageNG.getMatrice();

            int[][] fermeture = MorphoElementaire.fermeture(f_int, 8);
            int[][] seuil = Seuillage.seuillageSimple(fermeture, 150);
            int[][] erosion = MorphoElementaire.erosion(seuil, 20);
            int[] [] dilatation = MorphoElementaire.dilatation(erosion, 15);


            int[][] masqueBinaireGrand = MorphoComplexe.dilatationGeodesique(f_int, dilatation, 2);

            int[][] grand = new int[f_int.length][f_int[0].length];
            int[][] petit = new int[f_int.length][f_int[0].length];

            for (int i = 0; i < f_int.length; i++) {
                for (int j = 0; j < f_int[i].length; j++) {
                    int a = f_int[i][j] - masqueBinaireGrand[i][j];
                    grand[i][j] = Math.abs(a);
                    if (grand[i][j] == 0){
                        petit[i][j] = f_int[i][j];
                    } else {
                        petit[i][j] = 0;
                    }
                }
            }




            imageNG.setMatrice(petit);





//            JDialogAfficheMatriceDouble rougeDialog = new JDialogAfficheMatriceDouble(this, true, Utils.intToDouble(petit), "Petit");
//            rougeDialog.setVisible(true);
//            JDialogAfficheMatriceDouble bleuDialog = new JDialogAfficheMatriceDouble(this, true, Utils.intToDouble(grand2), "Grand");
//            bleuDialog.setVisible(true);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void jMenuEtape5_5ActionPerformed(java.awt.event.ActionEvent evt)
    {
        // gradient erosion + seuillage auto
        try {
            if (imageRGB != null) {
                imageNG = imageRGB.getCImageNG();
            }
            int[][] f_int = imageNG.getMatrice();
            int[][] grandientErosion = ContoursNonLineaire.gradientErosion(f_int);
            int[][] seuil = Seuillage.seuillageAutomatique(grandientErosion);
            imageNG.setMatrice(seuil);
            observer.setCImage(imageNG);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void jMenuEtape5_6ActionPerformed(java.awt.event.ActionEvent evt)
    {

    }
    private void jMenuEtape5_7ActionPerformed(java.awt.event.ActionEvent evt)
    {

    }

    private void jMenuHistogrammeAfficherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuHistogrammeAfficherActionPerformed
        int histo[];
        try
        {
            int f_int[][] = imageNG.getMatrice();
            histo = Histogramme.Histogramme256(f_int);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
            return;
        }

        // Création du dataset
        XYSeries serie = new XYSeries("Histo");
        for(int i=0 ; i<256 ; i++) serie.add(i,histo[i]);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serie);

        // Creation du chart
        JFreeChart chart = ChartFactory.createHistogram("Histogramme","Niveaux de gris","Nombre de pixels",dataset,PlotOrientation.VERTICAL,false,false,false);

        XYPlot plot = (XYPlot)chart.getXYPlot();
        ValueAxis axeX = plot.getDomainAxis();
        axeX.setRange(0,255);
        plot.setDomainAxis(axeX);

        // creation d'une frame
        ChartFrame frame = new ChartFrame("Histogramme de l'image",chart);
        frame.pack();
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuHistogrammeAfficherActionPerformed

    private void jMenuHistogrammeAfficherParamImageActionPerformed(java.awt.event.ActionEvent evt)
    {
        int Min = 0;
        int Max = 255;
        int Luminance = 0;
        double contraste1 = 0.0;
        double contraste2 = 0.0;

        try {
            if (imageNG == null)
            {
                Min = Histogramme.minimum(imageRGB.getCImageNG().getMatrice());
                Max = Histogramme.maximum(imageRGB.getCImageNG().getMatrice());
                Luminance = Histogramme.luminance(imageRGB.getCImageNG().getMatrice());
                contraste1 = Histogramme.contraste1(imageRGB.getCImageNG().getMatrice());
                contraste2 = Histogramme.contraste2(imageRGB.getCImageNG().getMatrice());
            }
            else
            {
                Min = Histogramme.minimum(imageNG.getMatrice());
                Max = Histogramme.maximum(imageNG.getMatrice());
                Luminance = Histogramme.luminance(imageNG.getMatrice());
                contraste1 = Histogramme.contraste1(imageNG.getMatrice());
                contraste2 = Histogramme.contraste2(imageNG.getMatrice());

            }

            //Afficher les r�sultas dans une fen�tre.
            System.out.println("Min : " + Min);
            System.out.println("Max : " + Max);
            System.out.println("Luminance : " + Luminance);
            System.out.println("contraste1 : " + contraste1);
            System.out.println("contraste2 : " + contraste2);

        } catch (CImageNGException e) {
            throw new RuntimeException(e);
        }
    }

    private void jMenuHistogrammeTraitementLineaireActionPerformed(java.awt.event.ActionEvent evt) {
        try
        {
            imageNG.setMatrice(Histogramme.rehaussement(imageNG.getMatrice(),Histogramme.creerCourbeTonaleLineaireSaturation(0, 255)));
        }
        catch (CImageNGException e) {
            throw new RuntimeException(e);
        }
    }

    private void jMenuHistogrammeTraitementGammaActionPerformed(java.awt.event.ActionEvent evt) {
        try
        {
            imageNG.setMatrice(Histogramme.rehaussement(imageNG.getMatrice(),Histogramme.creerCourbeTonaleGamma(2.0)));
        }
        catch (CImageNGException e) {
            throw new RuntimeException(e);
        }
    }

    private void jMenuHistogrammeTraitementNegatifActionPerformed(java.awt.event.ActionEvent evt) {
        try
        {
            imageNG.setMatrice(Histogramme.rehaussement(imageNG.getMatrice(),Histogramme.creeCourbeTonaleNegatif()));
        }
        catch (CImageNGException e) {
            throw new RuntimeException(e);
        }
    }

    private void jMenuHistogrammeTraitementEgalisationActionPerformed(java.awt.event.ActionEvent evt) {
        try
        {
            imageNG.setMatrice(Histogramme.rehaussement(imageNG.getMatrice(),Histogramme.creeCourbeTonaleEgalisation(imageNG.getMatrice())));
        }
        catch (CImageNGException e) {
            throw new RuntimeException(e);
        }
    }

    private void jMenuTraitementElementaireErosion(java.awt.event.ActionEvent evt) {

        System.out.println("jMenuTraitementElementaireErosion");

        try
        {

            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField jTextFieldFrequence = new JTextField();
            panel.add(jTextFieldFrequence);

            int result = JOptionPane.showConfirmDialog(null, panel, "Taille du masque",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                int TailleMasque = Integer.parseInt(jTextFieldFrequence.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Traitement non lineraire : Erosion");
                int[][] d = MorphoElementaire.erosion(f_int, TailleMasque);
                System.out.println("Fin Traitement non lineraire : Erosion");
                imageNG.setMatrice(d);

            } else {
                System.out.println("Cancelled");
            }
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }

    private void jMenuTraitementElementaireDilatation(java.awt.event.ActionEvent evt) {

        System.out.println("jMenuTraitementElementaireDilatation");

        try
        {

            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField jTextFieldFrequence = new JTextField();
            panel.add(jTextFieldFrequence);

            int result = JOptionPane.showConfirmDialog(null, panel, "Taille du masque",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                int TailleMasque = Integer.parseInt(jTextFieldFrequence.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Traitement non lineraire : Dilatation");
                int[][] d = MorphoElementaire.dilatation(f_int, TailleMasque);
                System.out.println("Fin Traitement non lineraire : Dilatation");
                imageNG.setMatrice(d);

            } else {
                System.out.println("Cancelled");
            }
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }

    private void jMenuTraitementElementaireOuverture(java.awt.event.ActionEvent evt) {

        System.out.println("jMenuTraitementElementaireOuverture");

        try
        {

            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField jTextFieldFrequence = new JTextField();
            panel.add(jTextFieldFrequence);

            int result = JOptionPane.showConfirmDialog(null, panel, "Taille du masque",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                int TailleMasque = Integer.parseInt(jTextFieldFrequence.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Traitement non lineraire : Ouverture");
                int[][] d = MorphoElementaire.ouverture(f_int, TailleMasque);
                System.out.println("Fin Traitement non lineraire : Ouverture");
                imageNG.setMatrice(d);

            } else {
                System.out.println("Cancelled");
            }
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }

    private void jMenuTraitementElementaireFermeture(java.awt.event.ActionEvent evt) {

        System.out.println("jMenuTraitementElementaireFermeture");

        try
        {

            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField jTextFieldFrequence = new JTextField();
            panel.add(jTextFieldFrequence);

            int result = JOptionPane.showConfirmDialog(null, panel, "Taille du masque",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                int TailleMasque = Integer.parseInt(jTextFieldFrequence.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Traitement non lineraire : Fermeture");
                int[][] d = MorphoElementaire.fermeture(f_int, TailleMasque);
                System.out.println("Fin Traitement non lineraire : Fermeture");
                imageNG.setMatrice(d);

            } else {
                System.out.println("Cancelled");
            }
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }
    private void ContoursNonLineairegradientErosion(java.awt.event.ActionEvent evt) {
        try
        {
            imageNG.setMatrice(ContoursNonLineaire.gradientErosion(imageNG.getMatrice()));
        }
        catch (CImageNGException e) {
            throw new RuntimeException(e);
        }
    }
    private void ContoursNonLineairegradientDilatation(java.awt.event.ActionEvent evt) {
        try
        {
            imageNG.setMatrice(ContoursNonLineaire.gradientDilatation(imageNG.getMatrice()));
        }
        catch (CImageNGException e) {
            throw new RuntimeException(e);
        }
    }
    private void ContoursNonLineairegradientBeucher(java.awt.event.ActionEvent evt) {
        try
        {
            imageNG.setMatrice(ContoursNonLineaire.gradientBeucher(imageNG.getMatrice()));
        }
        catch (CImageNGException e) {
            throw new RuntimeException(e);
        }
    }
    private void ContoursNonLineairelaplacienNonLineaire(java.awt.event.ActionEvent evt) {
        try
        {
            imageNG.setMatrice(ContoursNonLineaire.laplacienNonLineaire(imageNG.getMatrice()));
        }
        catch (CImageNGException e) {
            throw new RuntimeException(e);
        }
    }

    private void jMenuTraitementComplexeDilatationGeodesique(java.awt.event.ActionEvent evt) {

        System.out.println("jMenuTraitementComplexeDilatationGeodesique");

        try
        {

            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField JT_NbIter = new JTextField();
            panel.add(JT_NbIter);

            int result = JOptionPane.showConfirmDialog(null, panel, "Nombre d iterations",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {

                int NbIter = Integer.parseInt(JT_NbIter.getText());

                //Choisir une image comme masque geodesique
                CImageNG MasqueGeodesique = MaFctOuvrirImageNG();
                int[][] MatriceMasqueGeodesqiue = MasqueGeodesique.getMatrice();

                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Traitement non lineraire complexe : DilatationGeodesique");
                int[][] d = MorphoComplexe.dilatationGeodesique(f_int, MatriceMasqueGeodesqiue, NbIter);
                System.out.println("Fin Traitement non lineraire complexe : DilatationGeodesique");
                imageNG.setMatrice(d);

                }
                else {
                    System.out.println("Cancelled");
                }
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }

    private void jMenuTraitementComplexeReconstructionGeodesique(java.awt.event.ActionEvent evt) {

        System.out.println("jMenuTraitementComplexeReconstructionGeodesique");

        try
        {

            //Choisir une image comme masque geodesique
            CImageNG MasqueGeodesique = MaFctOuvrirImageNG();
            int[][] MatriceMasqueGeodesqiue = MasqueGeodesique.getMatrice();


            int MatriceImage[][] = imageNG.getMatrice();


            System.out.println("Debut Traitement non lineraire complexe : DilatationGeodesique");
            int[][] d = MorphoComplexe.reconstructionGeodesique(MatriceImage, MatriceMasqueGeodesqiue);
            System.out.println("Fin Traitement non lineraire complexe : DilatationGeodesique");
            imageNG.setMatrice(d);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }


    /*
    * Cette fonction permet de générer sous forme de programmation une image NG
    * Le but premier etait de creer une image binaire, moitié noir / moitié blanc pour l'utiliser comme masque geodesique
    * Cette fonction est appellee par le "Menu de test"
    * */
    private CImageNG MaFctOuvrirImageNG() {

        //Copie de celle du prof, mais retourne l'image plutot que de setter une variable globale avec l image
        JFileChooser choix = new JFileChooser();
        File fichier;

        choix.setCurrentDirectory(new File ("."));
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            fichier = choix.getSelectedFile();
            if (fichier != null)
            {
                try
                {
                    CImageNG MonMasqueGeodesique = new CImageNG(fichier);

                    //Je sais pas si j afficherai le masque, y a qu un observer
                    //observer.setCImage(imageNG);

                    return MonMasqueGeodesique;
                }
                catch (IOException ex)
                {
                    System.err.println("Erreur I/O : " + ex.getMessage());
                }
            }
        }
        return null; //On gerera les problemes plus tard, donc jamais
    }

    private void jMenuCreerImageNGEnCode(java.awt.event.ActionEvent evt)
    {

        System.out.println("Debut jMenuCreerImageNGEnCode");
        int [][] MaMatrice = new int[256][256];

        for (int y = 0; y < 128; y++) {
            for (int x = 0; x < 255; x++) {
                MaMatrice[y][x] = 0;
            }
        }

        for (int y = 129; y < 255; y++) {
            for (int x = 0; x < 255; x++) {
                MaMatrice[y][x] = 255;
            }
        }

        try {
            CImageNG MonImageNG = new CImageNG(MaMatrice);

            imageNG = MonImageNG;
            observer.setCImage(MonImageNG);
        } catch (CImageNGException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Fin jMenuCreerImageNGEnCode");

    }

    private void jMenuTraitementComplexeFiltreMedian(java.awt.event.ActionEvent evt) {

        System.out.println("jMenuTraitementComplexeFiltreMedian");

        try
        {

            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField jTextFieldFrequence = new JTextField();
            panel.add(jTextFieldFrequence);

            int result = JOptionPane.showConfirmDialog(null, panel, "Taille du masque",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                int TailleMasque = Integer.parseInt(jTextFieldFrequence.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Traitement non lineraire complexe : Filtre median");
                int[][] d = MorphoComplexe.filtreMedian(f_int, TailleMasque);
                System.out.println("Fin Traitement non lineraire complexe : Filtre median");
                imageNG.setMatrice(d);

            } else {
                System.out.println("Cancelled");
            }
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }


    private void jMenuFiltrageGlobalBasIdealActionPerformed(java.awt.event.ActionEvent evt) {
        try
        {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField jTextFieldFrequence = new JTextField();
            panel.add(jTextFieldFrequence);

            int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int freqence = Integer.parseInt(jTextFieldFrequence.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut filtrage Bas Ideal");
                int[][] filtrageBasIdeal = FiltrageLinaireGlobal.filtrePasseBasIdeal(f_int, freqence);
                System.out.println("Fin du filtrage Bas Ideal");
                filtrageBasIdeal = Utils.MiseAJourCImage(filtrageBasIdeal);
                imageNG.setMatrice(filtrageBasIdeal);
                // afficher dans une fenetre separer
                //JDialogAfficheFiltre dialogAfficheFiltre = new JDialogAfficheFiltre(this, true, Utils.intToDouble(filtrageBasIdeal), "filtrage passe-bas");
                //dialogAfficheFiltre.setVisible(true);

            } else {
                System.out.println("Cancelled");
            }


        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }
    private void jMenuFiltrageGlobalHautIdealActionPerformed(java.awt.event.ActionEvent evt) {
        try
        {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField jTextFieldFrequence = new JTextField();
            panel.add(jTextFieldFrequence);

            int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int freqence = Integer.parseInt(jTextFieldFrequence.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Filtrage Haut Ideal");
                int[][] filtrageHautIdeal = FiltrageLinaireGlobal.filtrePasseHautIdeal(f_int, freqence);
                System.out.println("Fin Filtrage Haut Ideal");
                filtrageHautIdeal = Utils.MiseAJourCImage(filtrageHautIdeal);
                imageNG.setMatrice(filtrageHautIdeal);
                // afficher dans une fenetre separer
                //JDialogAfficheFiltre dialogAfficheFiltre = new JDialogAfficheFiltre(this, true, Utils.intToDouble(filtrageHautIdeal), "filtrage passe-haut");
                //dialogAfficheFiltre.setVisible(true);
            } else {
                System.out.println("Cancelled");
            }

        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }
    private void jMenuFiltrageGlobalBasButterworthActionPerformed(ActionEvent event) {
        try
        {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JLabel freqLabel = new JLabel("Frequence: ");
            JTextField jTextFieldFrequence = new JTextField();
            JLabel ordreLabel = new JLabel("Ordre: ");
            JTextField jTextFieldOrdre = new JTextField();
            panel.add(freqLabel);
            panel.add(jTextFieldFrequence);
            panel.add(ordreLabel);
            panel.add(jTextFieldOrdre);

            int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int freqence = Integer.parseInt(jTextFieldFrequence.getText());
                int ordre = Integer.parseInt(jTextFieldOrdre.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Filtrage Bas butterworth");
                int[][] filtrageBasButter = FiltrageLinaireGlobal.filtrePasseBasButterworth(f_int, freqence, ordre);
                System.out.println("Fin Filtrage Haut butterworth");
                filtrageBasButter = Utils.MiseAJourCImage(filtrageBasButter);
                imageNG.setMatrice(filtrageBasButter);
                // afficher dans une fenetre separer
                //JDialogAfficheFiltre dialogAfficheFiltre = new JDialogAfficheFiltre(this, true, Utils.intToDouble(filtrageBasButter), "filtrage passe bas butterworth");
                //dialogAfficheFiltre.setVisible(true);
            } else {
                System.out.println("Cancelled");
            }

        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }
    private void jMenuFiltrageGlobalHautButterworthActionPerformed(ActionEvent event) {
        try
        {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JLabel freqLabel = new JLabel("Frequence: ");
            JTextField jTextFieldFrequence = new JTextField();
            JLabel ordreLabel = new JLabel("Ordre: ");
            JTextField jTextFieldOrdre = new JTextField();
            panel.add(freqLabel);
            panel.add(jTextFieldFrequence);
            panel.add(ordreLabel);
            panel.add(jTextFieldOrdre);

            int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int freqence = Integer.parseInt(jTextFieldFrequence.getText());
                int ordre = Integer.parseInt(jTextFieldOrdre.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Filtrage Haut butterworth");
                int[][] filtrageHautButter = FiltrageLinaireGlobal.filtrePasseHautButterworth(f_int, freqence, ordre);
                System.out.println("Fin Filtrage Haut butterworth");
                filtrageHautButter = Utils.MiseAJourCImage(filtrageHautButter);
                imageNG.setMatrice(filtrageHautButter);
                // afficher dans une fenetre separer
                //JDialogAfficheFiltre dialogAfficheFiltre = new JDialogAfficheFiltre(this, true, Utils.intToDouble(filtrageHautButter), "filtrage passe Haut butterworth");
                //dialogAfficheFiltre.setVisible(true);
            } else {
                System.out.println("Cancelled");
            }

        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }

    private void jMenuFiltrageLocalConvolutionActionPerformed(ActionEvent event) {
        try
        {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JLabel maskLabel = new JLabel("Masque: ");
            JTextField jTextFieldMasque = new JTextField();
            panel.add(maskLabel);
            panel.add(jTextFieldMasque);

            int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String v = jTextFieldMasque.getText();
                String[] arr = v.split(",");
                int length = (int) Math.sqrt(arr.length);
                double[][] masque = new double[length][length];

                int count = 0;
                for (int i = 0; i < masque.length; i++) {
                    for (int j = 0; j < masque[0].length; j++) {
                        double val = Double.parseDouble(arr[count]);
                        masque[i][j] = val;
                        count++;
                    }
                }

                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Filtrage Masque Convolution");
                int[][] filtrageConvolution = FiltrageLineaireLocal.filtreMasqueConvolution(f_int, masque);
                System.out.println("Fin Filtrage Masque Convolution");
                filtrageConvolution = Utils.MiseAJourCImage(filtrageConvolution);
                imageNG.setMatrice(filtrageConvolution);
                // afficher dans une fenetre separer
                //JDialogAfficheFiltre dialogAfficheFiltre = new JDialogAfficheFiltre(this, true, Utils.intToDouble(filtrageConvolution), "filtrage Masque Convolution");
                //dialogAfficheFiltre.setVisible(true);
            } else {
                System.out.println("Cancelled");
            }

        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }
    private void jMenuFiltrageLocalMoyenneurActionPerformed(ActionEvent event) {
        try
        {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JLabel maskLabel = new JLabel("Masque: ");
            JTextField jTextFieldMasque = new JTextField();
            panel.add(maskLabel);
            panel.add(jTextFieldMasque);

            int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int tailleMasque = Integer.valueOf(jTextFieldMasque.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Filtrage Masque Convolution");
                int[][] filtrageConvolution = FiltrageLineaireLocal.filtreMoyenneur(f_int, tailleMasque);
                System.out.println("Fin Filtrage Masque Convolution");
                filtrageConvolution = Utils.MiseAJourCImage(filtrageConvolution);
                imageNG.setMatrice(filtrageConvolution);
                // afficher dans une fenetre separer
                //JDialogAfficheFiltre dialogAfficheFiltre = new JDialogAfficheFiltre(this, true, Utils.intToDouble(filtrageConvolution), "filtrage Moyenneur");
                //dialogAfficheFiltre.setVisible(true);
            } else {
                System.out.println("Cancelled");
            }

        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }

    private void  jMenuContoursLineairePrewittActionPerformed(java.awt.event.ActionEvent evt){
        try
        {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JLabel freqLabel = new JLabel("Direction (1: horizontale, 2: verticale: ");
            JTextField jTextFieldDirection = new JTextField();
            panel.add(freqLabel);
            panel.add(jTextFieldDirection);

            int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int dir = Integer.parseInt(jTextFieldDirection.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Contours Prewitt");
                int[][] prewitt = ContoursLineaire.gradientPrewitt(f_int, dir);
                System.out.println("Fin Contours prewitt");
                prewitt = Utils.MiseAJourCImage(prewitt);
                imageNG.setMatrice(prewitt);
                // afficher dans une fenetre separer
                //JDialogAfficheFiltre dialogAfficheFiltre = new JDialogAfficheFiltre(this, true, Utils.intToDouble(filtrageHautButter), "filtrage passe Haut butterworth");
                //dialogAfficheFiltre.setVisible(true);
            } else {
                System.out.println("Cancelled");
            }

        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }
    private void  jMenuContoursLineaireSobelActionPerformed(java.awt.event.ActionEvent evt){
        try
        {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JLabel freqLabel = new JLabel("Direction (1: horizontale, 2: verticale: ");
            JTextField jTextFieldDirection = new JTextField();
            panel.add(freqLabel);
            panel.add(jTextFieldDirection);

            int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int dir = Integer.parseInt(jTextFieldDirection.getText());
                int f_int[][] = imageNG.getMatrice();
                System.out.println("Debut Contours sobel");
                int[][] sobel = ContoursLineaire.gradientSobel(f_int, dir);
                System.out.println("Fin Contours sobel");
                sobel = Utils.MiseAJourCImage(sobel);
                imageNG.setMatrice(sobel);
                // afficher dans une fenetre separer
                //JDialogAfficheFiltre dialogAfficheFiltre = new JDialogAfficheFiltre(this, true, Utils.intToDouble(filtrageHautButter), "filtrage passe Haut butterworth");
                //dialogAfficheFiltre.setVisible(true);
            } else {
                System.out.println("Cancelled");
            }

        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }
     private void  jMenuContoursLineaireLaplacien4ActionPerformed(java.awt.event.ActionEvent evt){
        try
        {
             int f_int[][] = imageNG.getMatrice();
             System.out.println("Debut leplacien 4");
             int[][] leplacien4 = ContoursLineaire.laplacien4(f_int);
             System.out.println("Fin le placien 4");
             leplacien4 = Utils.MiseAJourCImage(leplacien4);
             imageNG.setMatrice(leplacien4);
             // afficher dans une fenetre separer
            //JDialogAfficheFiltre dialogAfficheFiltre = new JDialogAfficheFiltre(this, true, Utils.intToDouble(filtrageHautButter), "filtrage passe Haut butterworth");
            //dialogAfficheFiltre.setVisible(true);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }
     private void  jMenuContoursLineaireLaplacien8ActionPerformed(java.awt.event.ActionEvent evt){
         try
         {
             int f_int[][] = imageNG.getMatrice();
             System.out.println("Debut leplacien 8");
             int[][] leplacien8 = ContoursLineaire.laplacien8(f_int);
             System.out.println("Fin le placien 8");
             leplacien8 = Utils.MiseAJourCImage(leplacien8);
             imageNG.setMatrice(leplacien8);
             // afficher dans une fenetre separer
             //JDialogAfficheFiltre dialogAfficheFiltre = new JDialogAfficheFiltre(this, true, Utils.intToDouble(filtrageHautButter), "filtrage passe Haut butterworth");
             //dialogAfficheFiltre.setVisible(true);
         }
         catch (CImageNGException ex)
         {
             System.out.println("Erreur CImageNG : " + ex.getMessage());
         }
    }

    private void jMenuSeuillageSeuillageSimpleActionPerformed(java.awt.event.ActionEvent evt) {
        try
        {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField jTextFieldSeuil = new JTextField();
            panel.add(jTextFieldSeuil);

            int result = JOptionPane.showConfirmDialog(null, panel, "Valeur du seuil",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                int seuil = Integer.parseInt(jTextFieldSeuil.getText());
                int MatriceImage[][] = imageNG.getMatrice();
                System.out.println("Debut Seuillage simple");
                int [][] ResulatSeuillageSimple = Seuillage.seuillageSimple(MatriceImage, seuil);
                System.out.println("Fin Seuillage simple");

                imageNG.setMatrice(ResulatSeuillageSimple);

            } else {
                System.out.println("Cancelled");
            }

        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }

    private void jMenuSeuillageSeuillageDoubleActionPerformed(java.awt.event.ActionEvent evt) {
        try
        {
            JPanel panel1 = new JPanel(new GridLayout(0, 1));
            JPanel panel2 = new JPanel(new GridLayout(0, 1));
            JTextField jTextFieldSeuil1 = new JTextField();
            JTextField jTextFieldSeuil2 = new JTextField();
            panel1.add(jTextFieldSeuil1);
            panel2.add(jTextFieldSeuil2);

            int result = JOptionPane.showConfirmDialog(null, panel1, "Valeur du seuil 1",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {

                result = JOptionPane.showConfirmDialog(null, panel2, "Valeur du seuil 2",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {

                    int seuil1 = Integer.parseInt(jTextFieldSeuil1.getText());
                    int seuil2 = Integer.parseInt(jTextFieldSeuil2.getText());
                    int MatriceImage[][] = imageNG.getMatrice();

                    System.out.println("Debut Seuillage double");
                    int [][] ResulatSeuillageDouble = Seuillage.seuillageDouble(MatriceImage, seuil1, seuil2);
                    System.out.println("Fin Seuillage double");

                    imageNG.setMatrice(ResulatSeuillageDouble);
                }
                else
                {
                    System.out.println("Cancelled");
                }
            }
            else
            {
                System.out.println("Cancelled");
            }
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }

    private void jMenuSeuillageSeuillageAutomatiqueActionPerformed(java.awt.event.ActionEvent evt)
    {
        try{

            System.out.println("Debut Seuillage automatique");
            int [][] ResulatSeuillageAutomatique = Seuillage.seuillageAutomatique(imageNG.getMatrice());
            System.out.println("Fin Seuillage automatique");

            imageNG.setMatrice(ResulatSeuillageAutomatique);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }


    private void activeMenusNG()
    {
        jMenuDessiner.setEnabled(true);
        jMenuFourier.setEnabled(true);
        jMenuHistogramme.setEnabled(true);

        //
        jMenuFiltrage.setEnabled(true);
        jMenuContours.setEnabled(true);
    }

    private void activeMenusRGB()
    {
        jMenuDessiner.setEnabled(true);
        jMenuFourier.setEnabled(false);
        jMenuHistogramme.setEnabled(false);
        jMenuContours.setEnabled(false);
        jMenuFiltrage.setEnabled(false);
    }

    private void jCheckBoxMenuItemDessinerCerclePleinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerCerclePleinActionPerformed
        if (!jCheckBoxMenuItemDessinerCerclePlein.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else
        {
            jCheckBoxMenuItemDessinerPixel.setSelected(false);
            jCheckBoxMenuItemDessinerLigne.setSelected(false);
            jCheckBoxMenuItemDessinerRectangle.setSelected(false);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(false);
            jCheckBoxMenuItemDessinerCercle.setSelected(false);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(true);
            observer.setMode(JLabelBeanCImage.SELECT_CERCLE_FILL);
        }
    }//GEN-LAST:event_jCheckBoxMenuItemDessinerCerclePleinActionPerformed

    private void jCheckBoxMenuItemDessinerCercleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerCercleActionPerformed
        if (!jCheckBoxMenuItemDessinerCercle.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else
        {
            jCheckBoxMenuItemDessinerPixel.setSelected(false);
            jCheckBoxMenuItemDessinerLigne.setSelected(false);
            jCheckBoxMenuItemDessinerRectangle.setSelected(false);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(false);
            jCheckBoxMenuItemDessinerCercle.setSelected(true);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(false);
            observer.setMode(JLabelBeanCImage.SELECT_CERCLE);
        }
    }//GEN-LAST:event_jCheckBoxMenuItemDessinerCercleActionPerformed

    private void jMenuItemFourierAfficherPartieImaginaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherPartieImaginaireActionPerformed
        try
        {
            int f_int[][] = imageNG.getMatrice();
            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
            for(int i=0 ; i<imageNG.getLargeur() ; i++)
                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);

            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double partieImaginaire[][] = fourier.getPartieImaginaire();

            JDialogAfficheMatriceDouble dialog = new JDialogAfficheMatriceDouble(this,true,partieImaginaire,"Fourier : Affichage de la partie imaginaire");
            dialog.setVisible(true);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItemFourierAfficherPartieImaginaireActionPerformed

    private void jMenuItemFourierAfficherPartieReelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherPartieReelleActionPerformed
        try
        {
            int f_int[][] = imageNG.getMatrice();
            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
            for(int i=0 ; i<imageNG.getLargeur() ; i++)
                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);

            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double partieReelle[][] = fourier.getPartieReelle();

            JDialogAfficheMatriceDouble dialog = new JDialogAfficheMatriceDouble(this,true,partieReelle,"Fourier : Affichage de la partie reelle");
            dialog.setVisible(true);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }//GEN-LAST:event_jMenuItemFourierAfficherPartieReelleActionPerformed

    private void jMenuItemFourierAfficherPhaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherPhaseActionPerformed
        try
        {
            int f_int[][] = imageNG.getMatrice();
            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
            for(int i=0 ; i<imageNG.getLargeur() ; i++)
                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);

            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double phase[][] = fourier.getPhase();

            JDialogAfficheMatriceDouble dialog = new JDialogAfficheMatriceDouble(this,true,phase,"Fourier : Affichage de la phase");
            dialog.setVisible(true);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }//GEN-LAST:event_jMenuItemFourierAfficherPhaseActionPerformed

    private void jMenuItemFourierAfficherModuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherModuleActionPerformed
        try
        {
            int f_int[][] = imageNG.getMatrice();
            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
            for(int i=0 ; i<imageNG.getLargeur() ; i++)
                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);

            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double module[][] = fourier.getModule();

            JDialogAfficheMatriceDouble dialog = new JDialogAfficheMatriceDouble(this,true,module,"Fourier : Affichage du module");
            dialog.setVisible(true);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItemFourierAfficherModuleActionPerformed

    private void jCheckBoxMenuItemDessinerPixelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerPixelActionPerformed
        if (!jCheckBoxMenuItemDessinerPixel.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else
        {
            jCheckBoxMenuItemDessinerPixel.setSelected(true);
            jCheckBoxMenuItemDessinerLigne.setSelected(false);
            jCheckBoxMenuItemDessinerRectangle.setSelected(false);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(false);
            jCheckBoxMenuItemDessinerCercle.setSelected(false);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(false);
            observer.setMode(JLabelBeanCImage.CLIC);
        }
    }//GEN-LAST:event_jCheckBoxMenuItemDessinerPixelActionPerformed

    private void jMenuItemEnregistrerSousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEnregistrerSousActionPerformed
        JFileChooser choix = new JFileChooser();
        File fichier;

        choix.setCurrentDirectory(new File ("."));
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            fichier = choix.getSelectedFile();
            if (fichier != null)
            {
                try
                {
                    if (imageRGB != null) imageRGB.enregistreFormatPNG(fichier);
                    if (imageNG != null)
                    {
                        System.out.println("Avant imageNG.enregistreFormatPNG(fichier)");
                        imageNG.enregistreFormatPNG(fichier);
                        System.out.println("Apres imageNG.enregistreFormatPNG(fichier)");
                    }
                }
                catch (IOException ex)
                {
                    System.err.println("Erreur I/O : " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemEnregistrerSousActionPerformed

    private void jMenuItemOuvrirNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOuvrirNGActionPerformed
        JFileChooser choix = new JFileChooser();
        File fichier;

        choix.setCurrentDirectory(new File ("."));
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            fichier = choix.getSelectedFile();
            if (fichier != null)
            {
                try
                {
                    imageNG = new CImageNG(fichier);
                    observer.setCImage(imageNG);
                    imageRGB = null;
                    activeMenusNG();
                }
                catch (IOException ex)
                {
                    System.err.println("Erreur I/O : " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemOuvrirNGActionPerformed

    private void jMenuItemNouvelleNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNouvelleNGActionPerformed
        JDialogNouvelleCImageNG dialog = new JDialogNouvelleCImageNG(this,true);
        dialog.setVisible(true);
        imageNG = dialog.getCImageNG();
        observer.setCImage(imageNG);
        imageRGB = null;
        activeMenusNG();
    }//GEN-LAST:event_jMenuItemNouvelleNGActionPerformed

    private void jMenuItemCouleurPinceauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCouleurPinceauActionPerformed
        if (imageRGB != null)
        {
            Color newC = JColorChooser.showDialog(this,"Couleur du pinceau",couleurPinceauRGB);
            if (newC != null) couleurPinceauRGB = newC;
            observer.setCouleurPinceau(couleurPinceauRGB);
        }

        if (imageNG != null)
        {
            JDialogChoixCouleurNG dialog = new JDialogChoixCouleurNG(this,true,couleurPinceauNG);
            dialog.setVisible(true);
            couleurPinceauNG = dialog.getCouleur();
        }
    }//GEN-LAST:event_jMenuItemCouleurPinceauActionPerformed

    private void jCheckBoxMenuItemDessinerRectanglePleinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerRectanglePleinActionPerformed
        if (!jCheckBoxMenuItemDessinerRectanglePlein.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else
        {
            jCheckBoxMenuItemDessinerPixel.setSelected(false);
            jCheckBoxMenuItemDessinerLigne.setSelected(false);
            jCheckBoxMenuItemDessinerRectangle.setSelected(false);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(true);
            jCheckBoxMenuItemDessinerCercle.setSelected(false);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(false);
            observer.setMode(JLabelBeanCImage.SELECT_RECT_FILL);
        }
    }//GEN-LAST:event_jCheckBoxMenuItemDessinerRectanglePleinActionPerformed

    private void jCheckBoxMenuItemDessinerRectangleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerRectangleActionPerformed
        if (!jCheckBoxMenuItemDessinerRectangle.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else
        {
            jCheckBoxMenuItemDessinerPixel.setSelected(false);
            jCheckBoxMenuItemDessinerLigne.setSelected(false);
            jCheckBoxMenuItemDessinerRectangle.setSelected(true);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(false);
            jCheckBoxMenuItemDessinerCercle.setSelected(false);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(false);
            observer.setMode(JLabelBeanCImage.SELECT_RECT);
        }
    }//GEN-LAST:event_jCheckBoxMenuItemDessinerRectangleActionPerformed

    private void jCheckBoxMenuItemDessinerLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerLigneActionPerformed
        if (!jCheckBoxMenuItemDessinerLigne.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else
        {
            jCheckBoxMenuItemDessinerPixel.setSelected(false);
            jCheckBoxMenuItemDessinerLigne.setSelected(true);
            jCheckBoxMenuItemDessinerRectangle.setSelected(false);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(false);
            jCheckBoxMenuItemDessinerCercle.setSelected(false);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(false);
            observer.setMode(JLabelBeanCImage.SELECT_LIGNE);
        }
    }//GEN-LAST:event_jCheckBoxMenuItemDessinerLigneActionPerformed

    private void jMenuItemNouvelleRGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNouvelleRGBActionPerformed
        JDialogNouvelleCImageRGB dialog = new JDialogNouvelleCImageRGB(this,true);
        dialog.setVisible(true);
        imageRGB = dialog.getCImageRGB();
        observer.setCImage(imageRGB);
        imageNG = null;
        activeMenusRGB();
    }//GEN-LAST:event_jMenuItemNouvelleRGBActionPerformed

    private void jMenuQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuQuitterActionPerformed

    private void jMenuItemOuvrirRGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOuvrirRGBActionPerformed
        JFileChooser choix = new JFileChooser();
        File fichier;

        choix.setCurrentDirectory(new File ("."));
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            fichier = choix.getSelectedFile();
            if (fichier != null)
            {
                try
                {
                    imageRGB = new CImageRGB(fichier);
                    observer.setCImage(imageRGB);
                    imageNG = null;
                    activeMenusRGB();
                }
                catch (IOException ex)
                {
                    System.err.println("Erreur I/O : " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemOuvrirRGBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IsilImageProcessing().setVisible(true);
            }
        });
    }

    public void ClicDetected(UnClicEvent e)
    {
        if (jCheckBoxMenuItemDessinerPixel.isSelected())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.setPixel(e.getX(),e.getY(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.setPixel(e.getX(),e.getY(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    public void SelectLigneDetected(DeuxClicsEvent e)
    {
        if (jCheckBoxMenuItemDessinerLigne.isSelected())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.DessineLigne(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.DessineLigne(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    public void SelectRectDetected(DeuxClicsEvent e)
    {
        if (jCheckBoxMenuItemDessinerRectangle.isSelected())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.DessineRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.DessineRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    public void SelectCercleDetected(DeuxClicsEvent e)
    {
        if (jCheckBoxMenuItemDessinerCercle.isSelected())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.DessineCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.DessineCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    public void SelectCercleFillDetected(DeuxClicsEvent e)
    {
        if (jCheckBoxMenuItemDessinerCerclePlein.isSelected())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.RemplitCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.RemplitCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    public void SelectRectFillDetected(DeuxClicsEvent e)
    {
        if (jCheckBoxMenuItemDessinerRectanglePlein.isSelected())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.RemplitRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.RemplitRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupDessiner;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerCercle;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerCerclePlein;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerLigne;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerPixel;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerRectangle;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerRectanglePlein;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuDessiner;
    private javax.swing.JMenu jMenuFourier;
    private javax.swing.JMenu jMenuFourierAfficher;
    private javax.swing.JMenu jMenuHistogramme;
    private javax.swing.JMenuItem jMenuHistogrammeAfficher;
    private javax.swing.JMenu jMenuImage;
    private javax.swing.JMenuItem jMenuItemCouleurPinceau;
    private javax.swing.JMenuItem jMenuItemEnregistrerSous;
    private javax.swing.JMenuItem jMenuItemFourierAfficherModule;
    private javax.swing.JMenuItem jMenuItemFourierAfficherPartieImaginaire;
    private javax.swing.JMenuItem jMenuItemFourierAfficherPartieReelle;
    private javax.swing.JMenuItem jMenuItemFourierAfficherPhase;
    private javax.swing.JMenuItem jMenuItemNouvelleNG;
    private javax.swing.JMenuItem jMenuItemNouvelleRGB;
    private javax.swing.JMenuItem jMenuItemOuvrirNG;
    private javax.swing.JMenuItem jMenuItemOuvrirRGB;
    private javax.swing.JMenu jMenuNouvelle;
    private javax.swing.JMenu jMenuOuvrir;
    private javax.swing.JMenuItem jMenuQuitter;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

    private javax.swing.JMenu jMenuFiltrage;
    private javax.swing.JMenu jMenuFiltrageGlobal;
    private javax.swing.JMenuItem jMenuFiltrageGlobalBasIdeal;
    private javax.swing.JMenuItem jMenuFiltrageGlobalHautIdeal;
    private javax.swing.JMenu jMenuSeuillage;
    private javax.swing.JMenuItem jMenuSeuillageSeuillageSimple;
    private javax.swing.JMenuItem jMenuSeuillageSeuillageDouble;
    private javax.swing.JMenuItem jMenuSeuillageSeuillageAutomatique;

    private javax.swing.JMenuItem jMenuFiltrageGlobalBasButterworth;
    private javax.swing.JMenuItem jMenuFiltrageGlobalHautButterworth;
    private javax.swing.JMenu jMenuFiltrageLocal;
    private javax.swing.JMenuItem jMenuFiltrageMasqueConvolution;
    private javax.swing.JMenuItem jMenuFiltrageMoyenneur;

    //Contour
    private javax.swing.JMenu jMenuContours;
    private javax.swing.JMenu jMenuContoursLineaire;
    private javax.swing.JMenuItem jMenuContoursLineairePrewitt;
    private javax.swing.JMenuItem jMenuContoursLineaireSobel;
    private javax.swing.JMenuItem jMenuContoursLineaireLaplacien4;
    private javax.swing.JMenuItem jMenuContoursLineaireLaplacien8;


    //Traitements non lineaire
    private javax.swing.JMenu jMenuTraitementNonLineaire;
    private javax.swing.JMenu jMenuTraitementElementaire;
    private javax.swing.JMenu jMenuContoursNonLineaire;
    private javax.swing.JMenu jMenuTraitementComplexe;
    private javax.swing.JMenuItem jMenuTraitementElementaireErosion;
    private javax.swing.JMenuItem jMenuTraitementElementaireDilatation;
    private javax.swing.JMenuItem jMenuTraitementElementaireOuverture;
    private javax.swing.JMenuItem jMenuTraitementElementaireFermeture;
    private javax.swing.JMenuItem jMenuMenuDeTest;
    private javax.swing.JMenuItem jMenuTraitementComplexeDilatationGeodesique;
    private javax.swing.JMenuItem jMenuTraitementComplexeReconstructionGeodesique;
    private javax.swing.JMenuItem jMenuContoursNonLineairegradientErosion;
    private javax.swing.JMenuItem jMenuContoursNonLineairegradientDilatation;
    private javax.swing.JMenuItem jMenuContoursNonLineairegradientBeucher;
    private javax.swing.JMenuItem jMenuContoursNonLineairelaplacienNonLineaire;
    private javax.swing.JMenuItem jMenuTraitementComplexeFiltreMedian;
    private javax.swing.JMenuItem jMenuCreerImageNGEnCode;


    //Histogramme
    private javax.swing.JMenuItem jMenuHistogrammeAfficherParamImage;
    private javax.swing.JMenuItem jMenuHistogrammeTraitementLineaire;
    private javax.swing.JMenuItem jMenuHistogrammeTraitementGamma;
    private javax.swing.JMenuItem jMenuHistogrammeTraitementNegatif;
    private javax.swing.JMenuItem jMenuHistogrammeTraitementEgalisation;

    //Etape 5

    private javax.swing.JMenu jMenuEtape5;
    private javax.swing.JMenuItem jMenuEtape5_1;
    private javax.swing.JMenuItem jMenuEtape5_2;
    private javax.swing.JMenuItem jMenuEtape5_3;
    private javax.swing.JMenuItem jMenuEtape5_4;
    private javax.swing.JMenuItem jMenuEtape5_5;
    private javax.swing.JMenuItem jMenuEtape5_6;
    private javax.swing.JMenuItem jMenuEtape5_7;

}

