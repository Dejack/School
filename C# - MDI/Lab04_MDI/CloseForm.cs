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
    public partial class CloseForm : Form {

        /// <summary>
        /// Prompts user to save when exiting
        /// </summary>
        public CloseForm() {
            InitializeComponent();
        }

        /// <summary>
        /// Closes form
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button2_Click(object sender, EventArgs e) {
            Close();
        }

        private void buttonOK_Click(object sender, EventArgs e) {
            DialogResult = DialogResult.OK;

            Close();
        }
    }
}
