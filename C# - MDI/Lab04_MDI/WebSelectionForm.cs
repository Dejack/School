/// <summary>
/// Author : Manben Chen
/// ID : A00937960
/// Version : 02/08/2016
/// 
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
using System.Net;
using System.IO;

namespace Lab04_MDI {
    public partial class WebSelectionForm : Form {

        /// <summary>
        /// Child of ParentForm, incharge of getting a url and displaying the image from that url.
        /// </summary>
        public WebSelectionForm() {
            InitializeComponent();
        }


        /// <summary>
        /// Closes the form on cancel.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonCancel_Click(object sender, EventArgs e) {
            Close();
        }

        /// <summary>
        /// Reads the content of textFieldLocation and attempts to open the url. Closes afterwards.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonOK_Click(object sender, EventArgs e) {
            try {
                WebRequest request = WebRequest.Create(textBoxLocation.Text);
                WebResponse response = request.GetResponse();
                Stream dataStream = response.GetResponseStream();
                ImageForm imageForm = new ImageForm();
                imageForm.theImage = Image.FromStream(dataStream);
                imageForm.MdiParent = ParentForm;
                imageForm.Text = "Web Form";
                imageForm.Show();

                Close();
                dataStream.Close();
                response.Close();

            } catch (WebException) {
                MessageBox.Show("Invalid url, cannot open");
            } catch (Exception) {
                MessageBox.Show("Unable to create image form from stream");
            }

        }
    }
}
