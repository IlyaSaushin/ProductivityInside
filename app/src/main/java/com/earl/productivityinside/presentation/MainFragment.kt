package com.earl.productivityinside.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.earl.productivityinside.App
import com.earl.productivityinside.R
import com.earl.productivityinside.databinding.FragmentMainBinding
import com.earl.productivityinside.presentation.LocaleHelper.setLocale
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    @Inject lateinit var viewModel: MainFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()
        initViews()
        binding.cityTitle.setOnClickListener {
            viewModel.getLocationByIp(getLocalIpAddress()!!)
        }
    }

    private fun initSpinner() {
        val spinner = binding.serverSpinner
        spinner.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.servers_name_spinner, R.layout.spinner_item)
    }

    private fun initViews() {
        binding.usaLang.setOnClickListener {
            val context = setLocale(requireContext(), "en")
            val resources = context.resources
            binding.cityTitle.setText(resources.getString(R.string.city))
            binding.temperature.setText(resources.getString(R.string.temperature))
        }

        binding.ruLang.setOnClickListener {
            val context = setLocale(requireContext(), "ru")
            val resources = context.resources
            binding.cityTitle.setText(resources.getString(R.string.city))
            binding.temperature.setText(resources.getString(R.string.temperature))
        }
    }

    fun getLocalIpAddress(): String? {
        try {
            val en: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf: NetworkInterface = en.nextElement()
                val enumIpAddr: Enumeration<InetAddress> = intf.getInetAddresses()
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress: InetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress() && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }

    fun getIpv4HostAddress(): String {
        NetworkInterface.getNetworkInterfaces()?.toList()?.map { networkInterface ->
            networkInterface.inetAddresses?.toList()?.find {
                !it.isLoopbackAddress && it is Inet4Address
            }?.let { return it.hostAddress }
        }
        return ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = MainFragment()
    }
}