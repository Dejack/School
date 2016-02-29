using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace COMP_3951_Lab_01 {
    public partial class Form1 : Form {

        private DateTime date1;
        private DateTime date2;

        public Form1() {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e) {

        }

        // Clears the textBox1 and textBox2 text fields
        private void clearButton_Click(object sender, EventArgs e) {
            textBox1.Text = "";
            textBox2.Text = "";
        }

        // Checks users input to see if dates are valid, if valid then display the difference between them.
        private void enterButton_Click(object sender, EventArgs e) {
            computeDate(); 
        }

        // Computes the difference between two dates
        private void computeDate() {
            bool textBoxOneValid = DateTime.TryParse(textBox1.Text, out date1);
            bool textBoxTwoValid = DateTime.TryParse(textBox2.Text, out date2);
            if (!textBoxOneValid && !textBoxTwoValid) {
                System.Windows.Forms.MessageBox.Show("Both textboxes are do not contain valid dates!");
            }
            else if (!textBoxOneValid) {
                System.Windows.Forms.MessageBox.Show("The textbox on the left contains a invalid date.");
            }
            else if (!textBoxTwoValid) {
                System.Windows.Forms.MessageBox.Show("The textbox on the right contains a invalid date.");
            }
            else if (date2.Subtract(date1).Days < 0) {
                System.Windows.Forms.MessageBox.Show("Error! The date on the left be before the date on the right.");
            }
            else {
                System.Windows.Forms.MessageBox.Show(date1.ToLongDateString() + " is " + date2.Subtract(date1).Days + " days apart from " + date2.ToLongDateString() + ".");
            }
        }

    }
}
