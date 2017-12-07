package ufrn.campusseguro.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ufrn.campusseguro.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentConfiguracao#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentConfiguracao extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public fragmentConfiguracao() {
        // Required empty public constructor
    }

    public static fragmentConfiguracao newInstance(int page) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragmentConfiguracao fragment = new fragmentConfiguracao();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuracao, container, false);
    }

}
