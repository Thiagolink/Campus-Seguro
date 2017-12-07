package ufrn.campusseguro.Fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Wesley Reuel on 05/12/2017.
 */

public class mainFragmentPagerAdapter extends FragmentPagerAdapter  {

    final int PAGE_COUNT = 3;

    private String tabTitles[];

    private Context context;

    private String getString(int id){
        return context.getResources().getString(id);

    }
    public mainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

        this.tabTitles = new String[] {
                "MAPA",
                "Estatistica",
                "Configuração"};

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    Fragment objFragment_map = null;
    Fragment objFragment_configuracao = null;
    Fragment objFragment_estatistica = null;


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (this.objFragment_map == null) {
                    //retornar nova instancia do framgmento MAPA
                    return fragmentMaps.newInstance(position + 1);
                } else {
                    return this.objFragment_map;
                }
            case 1:
                if (this.objFragment_estatistica == null) {
                    return fragmentEstatistica.newInstance(position + 1);
                } else {
                    return this.objFragment_estatistica;
                }
            case 2:
                if (this.objFragment_configuracao == null) {
                    return fragmentConfiguracao.newInstance(position + 1);
                } else {
                    return this.objFragment_configuracao;
                }
            default:
                if (this.objFragment_map == null) {
                    return fragmentMaps.newInstance(position + 1);
                } else {
                    return this.objFragment_map;
                }


        }
    }


    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Mapa";
            case 1:
                return "Estatistica";
            case 2:
                return "Configurações";
            default:
                return "Tela Inválida";
        }
    }
}
