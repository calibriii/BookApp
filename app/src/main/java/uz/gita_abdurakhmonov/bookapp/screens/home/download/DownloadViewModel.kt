package uz.gita_abdurakhmonov.bookapp.screens.home.download

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita_abdurakhmonov.bookapp.data.BookData
import uz.gita_abdurakhmonov.bookapp.domain.Repository
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val direction: DownloadContract.Direction,
    private val repository: Repository
):ViewModel(),DownloadContract.ViewModel {
    private lateinit var path:String
    override fun onEventDispatchers(intent: DownloadContract.Intent) {
        when(intent){
            is DownloadContract.Intent.ClickBack->{
                viewModelScope.launch {
                    direction.back()
                }
            }
            is DownloadContract.Intent.ClickNext->{
                viewModelScope.launch {
                    direction.nextToReader(intent.data.pdfUrl)
                }
            }
        }
    }

    override val container = container<DownloadContract.UIState, DownloadContract.SideEffect>(DownloadContract.UIState(null))
}
