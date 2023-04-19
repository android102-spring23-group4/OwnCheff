package com.example.owncheff

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    /*private var param1: String? = null
    private var param2: String? = null*/
    //val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
/*            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)*/
/*            if (acct != null){
                val personName: String? = acct.displayName
                val personGivenName: String? = acct.givenName
                val personFamilyName: String? = acct.familyName
                val personEmail: String? = acct.email
                val personId: String? = acct.id
                val personPhoto: Uri? = acct.photoUrl
            }*/
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())


            val personName: String? = acct?.displayName
            val personGivenName: String? = acct?.givenName
            val personFamilyName: String? = acct?.familyName
            val personEmail: String? = acct?.email
            val personId: String? = acct?.id
            val personPhoto: Uri? = acct?.photoUrl


        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        var firstNameTv = root.findViewById<TextView>(R.id.firstnameTv)
        val lastNameTv = root.findViewById<TextView>(R.id.lastnameTv)
        val emailTv = root.findViewById<TextView>(R.id.emailTv)
        val displayNameTv = root.findViewById<TextView>(R.id.displaynameTv)
        val profilepictureIv = root.findViewById<ImageView>(R.id.pfpIv)
        val userIdTv = root.findViewById<TextView>(R.id.userIdTv)

        firstNameTv.text = personGivenName
        lastNameTv.text = personFamilyName
        displayNameTv.text = personName
        emailTv.text = personEmail
        userIdTv.text = personId


        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}