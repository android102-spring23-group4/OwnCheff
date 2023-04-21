package com.example.owncheff

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.fido.fido2.api.common.RequestOptions


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
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
/*
        val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())
*/
        val acct = arguments?.getParcelable<GoogleSignInAccount>("google_account")

        val personEmail = arguments?.getString("email")
        val personGivenName = arguments?.getString("firstname")
        val personName = arguments?.getString("name")
        val personFamilyName = arguments?.getString("lastname")
        val profilepictureurl = arguments?.getString("profilepic")

/*        val personName: String? = acct?.displayName
        val personGivenName: String? = acct?.givenName
        val personFamilyName: String? = acct?.familyName
        val personEmail: String? = acct?.email
        val personPhoto: Uri? = acct?.photoUrl*/

        /*val signInClient = GoogleSignIn.getClient(this,gso)*/

        val lastNameTv = root.findViewById<TextView>(R.id.lastnameTv)
        val emailTv = root.findViewById<TextView>(R.id.emailTv)
        val displayNameTv = root.findViewById<TextView>(R.id.displaynameTv)
        val profilepictureIv = root.findViewById<ImageView>(R.id.pfpIv)
        val signoutBtn = root.findViewById<Button>(R.id.signoutBtn)

        lastNameTv.text = personGivenName+" "+personFamilyName
        displayNameTv.text = personName
        emailTv.text = personEmail


        Glide.with(this)
            .load(profilepictureurl.toString())
            .transition(DrawableTransitionOptions.withCrossFade())
            .override(400, 400)
            .circleCrop()
            .into(profilepictureIv)

        signoutBtn.setOnClickListener {
            // Sign out the user from Google
            val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(requireContext(), GoogleSignInOptions.DEFAULT_SIGN_IN)
            googleSignInClient.signOut().addOnCompleteListener {
                // Launch LoginActivity after sign-out
                val intent = Intent(activity, GoogleLoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }


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