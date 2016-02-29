/// <summary>
/// Author : Manben Chen
/// ID : A00937960
/// Version : 02/08/2016
/// </summary>

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing.Imaging;

namespace Lab04_MDI {
    public partial class ParentForm : Form {

        /// <summary>
        /// ParentForm that contains maybe children forms. 
        /// </summary>
        public ParentForm() {
            InitializeComponent();

        }

        /// <summary>
        /// Opens a new SizeSelection form
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void newToolStripMenuItem_Click(object sender, EventArgs e) {
            SizeSelectionForm sizeForm;

            sizeForm = new SizeSelectionForm();
            sizeForm.MdiParent = this;
            sizeForm.Show();
        }

        /// <summary>
        /// Opening a image file on your computer.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void openFromFileToolStripMenuItem_Click(object sender, EventArgs e) {
            OpenFileDialog fileDialog = new OpenFileDialog();

            if (fileDialog.ShowDialog() == DialogResult.OK) {
                try {
                    ImageForm newImage = new ImageForm();
                    newImage.MdiParent = this;
                    newImage.path = fileDialog.FileName;
                    newImage.theImage = Image.FromFile(fileDialog.FileName);
                    newImage.Text = fileDialog.FileName;
                    newImage.Show();
                } catch (Exception) {
                    MessageBox.Show("Invalid file type!");
                }
            }
        }

        /// <summary>
        /// Opening a image file from the web.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void openFromWebToolStripMenuItem_Click(object sender, EventArgs e) {
            WebSelectionForm webForm = new WebSelectionForm();
            webForm.MdiParent = this;
            webForm.Show();
        }

        /// <summary>
        /// Checks to see if any savable forms are open, if not save and save as will not be usable.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void fileToolStripMenuItem_DropDownOpening(object sender, EventArgs e) {
            FormCollection allOpenForms = Application.OpenForms;
            bool savableForms = false; 
            foreach (Form form in allOpenForms) {
                if (checkSavableForms(form)) {
                    savableForms = true;
                }
            }
            if (savableForms) {
                saveAsToolStripMenuItem.Enabled = true;
                saveToolStripMenuItem.Enabled = true;
            } else {
                saveAsToolStripMenuItem.Enabled = false;
                saveToolStripMenuItem.Enabled = false;
            }
        }

        /// <summary>
        /// Checks if the form is not a instance of ParentForm, WebSelectionForm, and SizeSelectionForm
        /// </summary>
        /// <param name="form"></param>
        /// <returns></returns>
        private bool checkSavableForms(Form form) {
            if (!(form.GetType() == typeof(SizeSelectionForm)) &&
                    !(form.GetType() == typeof(WebSelectionForm)) &&
                    !(form.GetType() == typeof(ParentForm))) {
                return true;
            } else {
                return false;
            }
        }

        /// <summary>
        /// When save button is pressed. If the file already exists automically saves. If no file exists chooses a directory and saves it there.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void saveToolStripMenuItem_Click(object sender, EventArgs e) {
            ImageForm activeChildForm = (ImageForm) ActiveMdiChild;
            SaveFileDialog saveDialog = new SaveFileDialog();
            saveDialog.Title = "Save an Image File";
            saveDialog.Filter = "Jpeg Image|*.jpg|Bitmap Image|*.bmp|Gif Image|*.gif|PNG Image|*.png";
            saveDialog.FileName = activeChildForm.Text;
            if (activeChildForm.Text.Equals("sizeSelectionForm") || activeChildForm.Text.Equals("WebSelectionForm")) {
                return;
            }
            if (activeChildForm.Text.Equals("Web Form") || activeChildForm.Text.Equals("imageForm")) { 
              
                if (saveDialog.ShowDialog() == DialogResult.OK) {
                        try {
                            activeChildForm.theImage.Save(saveDialog.FileName);
                        } catch (Exception exception) {
                            MessageBox.Show(exception.Message);
                        }
                }
            } else {
                try {
                    Image newImage = new Bitmap(activeChildForm.Width, activeChildForm.Height);
                    String formatString = Path.GetExtension(saveDialog.FileName);
                    ImageFormat format;
                    if (formatString.Equals(".jpg") || formatString.Equals(".jpeg")) {
                        format = ImageFormat.Jpeg;
                    } else if (formatString.Equals(".png")) {
                        format = ImageFormat.Png;
                    } else if (formatString.Equals(".bmp")) {
                        format = ImageFormat.Bmp;
                    } else {
                        format = ImageFormat.Gif;
                    }

                    Graphics g = Graphics.FromImage(newImage);
                    g.DrawImage(activeChildForm.theImage, 0, 0);
                    activeChildForm.theImage.Dispose();
                    activeChildForm.theImage = newImage;
                    activeChildForm.theImage.Save(saveDialog.FileName, format);
                } catch (Exception exception) {
                    MessageBox.Show(exception.Message);
                }
               
            }
        }
        /// <summary>
        /// Opens up a directory to save the file at.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void saveAsToolStripMenuItem_Click(object sender, EventArgs e) {
            ImageForm activeChildForm = (ImageForm)ActiveMdiChild;
            SaveFileDialog saveDialog = new SaveFileDialog();
            saveDialog.Title = "Save an Image File";
            saveDialog.Filter = "Jpeg Image|*.jpg|Bitmap Image|*.bmp|Gif Image|*.gif|PNG Image|*.png";
            saveDialog.FileName = activeChildForm.Text;

            if (activeChildForm.Text.Equals("sizeSelectionForm") || activeChildForm.Text.Equals("WebSelectionForm")) {
                return;
            }
            if (saveDialog.ShowDialog() == DialogResult.OK) {
                try {
                    Image newImage = new Bitmap(activeChildForm.Width, activeChildForm.Height);
                    String formatString = Path.GetExtension(saveDialog.FileName);
                    ImageFormat format;
                    if (formatString.Equals(".jpg") || formatString.Equals(".jpeg")) {
                        format = ImageFormat.Jpeg;
                    } else if (formatString.Equals(".png")) {
                        format = ImageFormat.Png;
                    } else if (formatString.Equals(".bmp")) {
                        format = ImageFormat.Bmp;
                    } else {
                        format = ImageFormat.Gif;
                    }

                    Graphics g = Graphics.FromImage(newImage);
                    g.DrawImage(activeChildForm.theImage, 0, 0);
                    activeChildForm.theImage.Dispose();
                    activeChildForm.theImage = newImage;
                    activeChildForm.theImage.Save(saveDialog.FileName, format);
                } catch (Exception exception) {
                    MessageBox.Show(exception.Message);
                }
            }
        }

        /// <summary>
        /// Exits the program. If any child forms are open ask user to save.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void exitToolStripMenuItem_Click(object sender, EventArgs e) {
            if (MdiChildren.Length > 0) {
                foreach (Form form in MdiChildren) {
                    if (!form.Text.Equals("sizeSelectionForm") && !form.Text.Equals("Web Form")) {
                        CloseForm closeForm = new CloseForm();
                        if (closeForm.ShowDialog() == DialogResult.OK) {
                            saveAsToolStripMenuItem_Click(null, null);
                        }
                        closeForm.Close();
                    }

                }
            }
            Application.Exit();

        }

        /// <summary>
        /// Arranges child forms as cascade
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cascadeToolStripMenuItem_Click(object sender, EventArgs e) {
            LayoutMdi(MdiLayout.Cascade);
        }

        /// <summary>
        /// Arranges child forms horizontally
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void tileHorizontalToolStripMenuItem_Click(object sender, EventArgs e) {
            LayoutMdi(MdiLayout.TileHorizontal);
        }

        /// <summary>
        /// Arranges child forms vertically
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void tileVerticalToolStripMenuItem_Click(object sender, EventArgs e) {
            LayoutMdi(MdiLayout.TileVertical);
        }
    }
}
