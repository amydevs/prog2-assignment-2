{ pkgs ? import ./pkgs.nix {}, ci ? false }:

with pkgs;
mkShell {
  nativeBuildInputs = [
    # you're going to have to install this manually and it's gonna hurt bucko
    oraclejdk8
    maven
    xorg.libX11
    xorg.libXtst
  ];
  APPEND_LIBRARY_PATH = "${lib.makeLibraryPath [ libGL xorg.libXtst ]}";
  shellHook = ''
    export LD_LIBRARY_PATH="$APPEND_LIBRARY_PATH:$LD_LIBRARY_PATH"

    set -o allexport
    . ./.env
    set +o allexport
    set -v
    ${
      lib.optionalString ci
      ''
      set -o errexit
      set -o nounset
      set -o pipefail
      shopt -s inherit_errexit
      ''
    }
    mkdir --parents "$(pwd)/tmp"
    
    set +v
  '';
}
