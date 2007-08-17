package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.sound.sampled.Mixer;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import math.DFT;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Settings extends JFrame implements ActionListener {
	private ResourceManager rm;
	private JComboBox comboBoxInputDevice;
	private JComboBox ComboBoxNHops;
	private JComboBox comboBoxWindowSize;
	private JLabel labelRefreshRate;
	private JPanel panelDisplay;
	private JTextField textRefreshRate;
	private JPanel panelDFTParameters;
	private JPanel panelAudioDevices;
	private JComboBox comboBoxWindowType;
	private JButton buttonOK;
	private JButton buttonApply;
	private JLabel labelNHops;
	private JLabel labelWindowSize;
	private JLabel labelWindowType;
	private JLabel labelOutputDevice;
	private JLabel labelInputDevice;
	private JComboBox comboBoxOutputDevices;
	
	private int displayRefreshRate;
	private int windowType;
	private JCheckBox checkBoxAntiAlias;
	private JTextField textThreshold;
	private JLabel labelThresholdLevel;
	private JPanel panelTwoWayMismatch;
	private JTabbedPane tabbedPane1;
	private int windowSize;
	private int numberOfHops;
	
	private Mixer.Info[] mixerInfos;
	private String[] inputInfos;
	private String[] outputInfos;

	public Settings(ResourceManager rm) {
		this.rm = rm;
		displayRefreshRate = rm.getDisplay().getRefreshRate();
		windowType = rm.getAudioEngine().getAudioAnalyser().getWindowType();
		windowSize = rm.getAudioEngine().getAudioAnalyser().getWindowSize();
		numberOfHops = rm.getAudioEngine().getAudioAnalyser().getNumberOfHops();
		
		Mixer.Info[] mixerInfos = rm.getAudioEngine().getInputMixerInfos();
		inputInfos = new String[mixerInfos.length];
		for (int i = 0; i < mixerInfos.length; i++) {
			 inputInfos[i] = mixerInfos[i].getName();
		}
		
		mixerInfos = rm.getAudioEngine().getOutputMixerInfos();
		outputInfos = new String[mixerInfos.length];
		for (int i = 0; i < mixerInfos.length; i++) {
			 outputInfos[i] = mixerInfos[i].getName();
		}
		
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("Settings");
				this.setSize(512, 265);
				this.setLocation(500,100);
			}
			{
				tabbedPane1 = new JTabbedPane();
				getContentPane().add(tabbedPane1);
				
				tabbedPane1.setBounds(0, 0, 504, 231);
				{
					panelAudioDevices = new JPanel();
					tabbedPane1.addTab("AudioDevices", null, panelAudioDevices, null);
					panelAudioDevices.setLayout(null);
					panelAudioDevices.setBounds(378, 133, 273, 175);
					panelAudioDevices.setPreferredSize(new java.awt.Dimension(462, 175));
					{
						labelInputDevice = new JLabel();
						panelAudioDevices.add(labelInputDevice);
						labelInputDevice.setText("Input Device:");
						labelInputDevice.setBounds(14, 14, 119, 14);
					}
					{
						ComboBoxModel comboBoxInputDeviceModel = new DefaultComboBoxModel(
						//new String[] { "AudioDevice1", "Input2", "Soundcard3"}
							inputInfos);
						comboBoxInputDevice = new JComboBox();
						panelAudioDevices.add(comboBoxInputDevice);
						comboBoxInputDevice.setModel(comboBoxInputDeviceModel);
						comboBoxInputDevice.setBounds(14, 28, 210, 21);
						comboBoxInputDevice.addActionListener(this);
					}
					{
						labelOutputDevice = new JLabel();
						panelAudioDevices.add(labelOutputDevice);
						labelOutputDevice.setText("Output Device:");
						labelOutputDevice.setBounds(14, 56, 119, 14);
					}
					{
						ComboBoxModel comboBoxOutputDevicesModel = new DefaultComboBoxModel(
						//						new String[] { "Outputcard1", "Item Two" }
							outputInfos);
						comboBoxOutputDevices = new JComboBox();
						panelAudioDevices.add(comboBoxOutputDevices);
						comboBoxOutputDevices
							.setModel(comboBoxOutputDevicesModel);
						comboBoxOutputDevices.setBounds(14, 73, 210, 21);
						comboBoxOutputDevices.addActionListener(this);
					}
				}
				{
					panelDFTParameters = new JPanel();
					tabbedPane1.addTab("DFT Parameters", null, panelDFTParameters, null);
					panelDFTParameters.setLayout(null);
					panelDFTParameters.setBounds(266, 7, 231, 105);
					panelDFTParameters.setBorder(BorderFactory
						.createTitledBorder("DFT Parameters"));
					panelDFTParameters.setPreferredSize(new java.awt.Dimension(258, 205));
					{
						labelWindowType = new JLabel();
						panelDFTParameters.add(labelWindowType);
						labelWindowType.setText("Window Type:");
						labelWindowType
							.setPreferredSize(new java.awt.Dimension(102, 14));
						labelWindowType.setBounds(10, 26, 102, 14);
					}
					{
						ComboBoxModel comboBoxWindowTypeModel = new DefaultComboBoxModel(
							new String[] { "Rectangular", "Hann", "Hamming",
									"Keiser", "Blackmann" });
						comboBoxWindowType = new JComboBox();
						panelDFTParameters.add(comboBoxWindowType);
						comboBoxWindowType.setModel(comboBoxWindowTypeModel);
						comboBoxWindowType.setBounds(112, 21, 98, 21);
						comboBoxWindowType
							.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								switch (comboBoxWindowType.getSelectedIndex()) {
								case 0:	
									rm.getAudioEngine().getAudioAnalyser().setWindowType( DFT.RECTANGULAR );
									break;
								case 1:
									rm.getAudioEngine().getAudioAnalyser().setWindowType( DFT.HANN );
								case 2:
									// Hamming window yarat
									rm.getAudioEngine().getAudioAnalyser().setWindowType( DFT.HANN );
									break;
								case 3:
									// Keiser yarat
									rm.getAudioEngine().getAudioAnalyser().setWindowType( DFT.HANN );
									break;
								case 4:
									rm.getAudioEngine().getAudioAnalyser().setWindowType( DFT.BLACKMANN );
									break;									
								default:
									break;
								}
								System.out
									.println(comboBoxWindowType.getSelectedIndex()
											+ " " + 
											comboBoxWindowType.getSelectedItem());
							}
							});
					}
					{
						labelWindowSize = new JLabel();
						panelDFTParameters.add(labelWindowSize);
						labelWindowSize.setText("Window Size");
						labelWindowSize.setBounds(10, 56, 105, 14);
					}
					{
						ComboBoxModel comboBoxWindowSizeModel = new DefaultComboBoxModel(
							new String[] { "128", "256", "512", "1024", "2048",
									"4096", "8192" });
						comboBoxWindowSize = new JComboBox();
						panelDFTParameters.add(comboBoxWindowSize);
						comboBoxWindowSize.setModel(comboBoxWindowSizeModel);
						String size = Integer.toString(windowSize);
						comboBoxWindowSize.setSelectedItem(size);
						comboBoxWindowSize.setBounds(112, 56, 98, 21);
						comboBoxWindowSize
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									String str = ((String) ((JComboBox) evt
										.getSource()).getSelectedItem());
									windowSize = Integer.parseInt(str);
								}
							});
					}
					{
						labelNHops = new JLabel();
						panelDFTParameters.add(labelNHops);
						labelNHops.setText("# of Hops");
						labelNHops.setBounds(10, 91, 105, 14);
					}
					{
						ComboBoxModel ComboBoxNHopsModel = new DefaultComboBoxModel(
							new String[] { "1", "2", "3", "4", "5", "6", "7",
									"8" });
						ComboBoxNHops = new JComboBox();
						panelDFTParameters.add(ComboBoxNHops);
						ComboBoxNHops.setModel(ComboBoxNHopsModel);
						ComboBoxNHops.setSelectedIndex(numberOfHops - 1);
						ComboBoxNHops.setBounds(112, 91, 98, 21);
						ComboBoxNHops.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								int n = ((JComboBox) evt.getSource())
									.getSelectedIndex();
								numberOfHops = n + 1;
							}
						});
					}
					{
						buttonOK = new JButton();
						panelDFTParameters.add(buttonOK);
						buttonOK.setText("OK");
						buttonOK.setBounds(21, 140, 63, 28);
						buttonOK.addActionListener(this);
					}
					{
						buttonApply = new JButton();
						panelDFTParameters.add(buttonApply);
						buttonApply.setText("Apply");
						buttonApply.setBounds(105, 140, 77, 28);
						buttonApply.addActionListener(this);
					}
				}
				{
					panelDisplay = new JPanel();
					tabbedPane1.addTab("Display", null, panelDisplay, null);
					panelDisplay.setBounds(392, 112, 133, 84);
					panelDisplay.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createTitledBorder(""),
						"Display",
						TitledBorder.LEADING,
						TitledBorder.TOP));
					panelDisplay.setLayout(null);
					{
						textRefreshRate = new JTextField();
						panelDisplay.add(textRefreshRate);
						textRefreshRate.setText(Integer
							.toString(displayRefreshRate));
						textRefreshRate.setBounds(14, 28, 42, 21);
						textRefreshRate.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								String input = ((JTextField) evt.getSource())
									.getText();
								displayRefreshRate = Integer.parseInt(input);
							}
						});
					}
					{
						labelRefreshRate = new JLabel();
						panelDisplay.add(labelRefreshRate);
						labelRefreshRate.setText("Refresh Rate (ms)");
						labelRefreshRate.setBounds(63, 28, 140, 21);
					}
					{
						checkBoxAntiAlias = new JCheckBox("Anti-aliased lines", true);
						panelDisplay.add(checkBoxAntiAlias);
						checkBoxAntiAlias.setBounds(42, 56, 168, 21);
						checkBoxAntiAlias
							.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								rm.getDisplay().setAntialiased( checkBoxAntiAlias.isSelected() );
							}
							});
					}
				}
				{
					panelTwoWayMismatch = new JPanel();
					tabbedPane1.addTab(
						"Two-Way Mismatch",
						null,
						panelTwoWayMismatch,
						null);
					panelTwoWayMismatch.setLayout(null);
					{
						labelThresholdLevel = new JLabel();
						panelTwoWayMismatch.add(labelThresholdLevel);
						labelThresholdLevel.setText("Threshold Level");
						labelThresholdLevel.setBounds(14, 21, 119, 21);
					}
					{
						textThreshold = new JTextField();
						panelTwoWayMismatch.add(textThreshold);
						textThreshold.setText("10");
						textThreshold.setBounds(98, 21, 63, 21);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void apply() {
		rm.getDisplay().setRefreshRate(displayRefreshRate);
		rm.getAudioEngine().getAudioAnalyser().setWindowSizeAndNumberOfHops(windowSize, numberOfHops);
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==buttonApply) {
			apply();
		}
		else if( ae.getSource()==buttonOK ) {
			apply();
			this.setVisible(false);
		}
		else if( ae.getSource()==comboBoxInputDevice ) {
			//System.out.println(comboBoxInputDevice.getSelectedItem().toString());
			rm.getAudioEngine().changeInputAndOutputLine(comboBoxInputDevice.getSelectedIndex(), comboBoxOutputDevices.getSelectedIndex());
		}
		else if( ae.getSource() == comboBoxOutputDevices ) {
			rm.getAudioEngine().changeInputAndOutputLine(comboBoxInputDevice.getSelectedIndex(), comboBoxOutputDevices.getSelectedIndex());
		}
		
	}

}