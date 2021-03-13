package es.nauticapps.iduscalendas.di

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.nauticapps.iduscalendas.data.config.local.IdusDatabase
import es.nauticapps.iduscalendas.data.config.local.IdusLocal
import es.nauticapps.iduscalendas.data.config.network.IdusNetwork
import es.nauticapps.iduscalendas.data.config.network.IdusRetrofit
import es.nauticapps.iduscalendas.data.config.network.IdusService
import es.nauticapps.iduscalendas.data.config.network.NetworkManager
import es.nauticapps.iduscalendas.data.config.network.auth.IdusRetrofitAuth
import es.nauticapps.iduscalendas.data.config.network.auth.OAuthLocal
import es.nauticapps.iduscalendas.data.config.network.auth.RefreshTokenAuthenticate
import es.nauticapps.iduscalendas.data.idus.repository.IdusRepositoryImpl
import es.nauticapps.iduscalendas.domain.IdusRepository


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    //@Provides
    //fun providesIdusRetrofit(): IdusRetrofit = IdusRetrofit()

    //@Provides
    //fun providesRetrofit(idusRetrofit: IdusRetrofit) : Retrofit = idusRetrofit.loadRetrofit()

    //@Provides
    //fun providesIdusService(retrofit: Retrofit) : IdusService = retrofit.create(IdusService::class.java)

    //@Provides
    //fun provideIdusNetwork(service: IdusService): IdusNetwork = IdusNetwork(service)

    //@Provides
    //fun provideIdusRemoteRepositoryImpl(network: IdusNetwork) : IdusRemoteRepositoryImpl = IdusRemoteRepositoryImpl(network)

    //@Provides
    //fun provideIdusRepositoryImpl(remote: IdusRemoteRepositoryImpl) : IdusRepositoryImpl = IdusRepositoryImpl(remote)

    //@Provides
    //fun provideIdusRepository(remote: IdusRemoteRepositoryImpl) :  IdusRepository = IdusRepositoryImpl(remote)

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences("local", Activity.MODE_PRIVATE)

    @Provides
    fun provideOAuthLocal(sharedPreferences: SharedPreferences): OAuthLocal = OAuthLocal(sharedPreferences)

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context


    @Provides
    fun provideIdusRepository(context: Context, auth: RefreshTokenAuthenticate, authLocal: OAuthLocal) : IdusRepository = IdusRepositoryImpl(
        NetworkManager(context),
        IdusNetwork(IdusRetrofit(auth, authLocal, IdusRetrofitAuth().loadRetrofit()).loadRetrofit().create(IdusService::class.java)),
        IdusLocal(IdusDatabase(context).loadDatabase().idusDao())
    )

}