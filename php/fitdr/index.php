function Redirect($url, $permanent = false)
{
    header('Location: ' . $url, true, $permanent ? 301 : 302);

    exit();
}

Redirect('https://10.31.1.46:8443/fitdr-0.1', false);