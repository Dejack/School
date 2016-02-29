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
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab04_MDI {

    /// <summary>
    /// Child of ParentForm, allows user to create a new window of varying sizes 
    /// </summary>
    public partial class SizeSelectionForm : Form {

        public const int SMALL_FORM_LENGTH = 640;
        public const int SMALL_FORM_HEIGHT = 480;
        public const int MEDIUM_FORM_LENGTH = 800;
        public const int MEDIUM_FORM_HEIGHT = 600;
        public const int LARGE_FORM_LENGTH = 1024;
        public const int LARGE_FORM_HEIGHT = 768;

        public SizeSelectionForm() {
            InitializeComponent();
        }

        /// <summary>
        /// Cancel button, closes the form on click
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonCancel_Click(object sender, EventArgs e) {
            Close();
        }

        // Creates a new image form based on the chosen size by radio button
        private void buttonOK_Click(object sender, EventArgs e) {
            ImageForm imageChild = new ImageForm();
            imageChild.MdiParent = ParentForm;

            if (radioButtonSmall.Checked) {
                imageChild.ClientSize = new Size(SMALL_FORM_LENGTH, SMALL_FORM_HEIGHT);
            } else if (radioButtonMedium.Checked) {
                imageChild.ClientSize = new Size(MEDIUM_FORM_LENGTH, MEDIUM_FORM_HEIGHT);
            } else {
                imageChild.ClientSize = new Size(LARGE_FORM_LENGTH, LARGE_FORM_HEIGHT);
            }
            imageChild.BackColor = Color.Blue;
            imageChild.Show();
            Close();
        }
    }
}
