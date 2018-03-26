@echo off
@rem ================================================================================================
@rem --------- INSTALL.BAT: Batch File to Download dependencies. File Size ~ 140 MB ---------
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.

@rem ---------- INSTRUCTIONS: ------------
@rem 1. Run 'install.bat'. 
@rem 2. Extract the jars.rar files into '/jars' folder. 
@rem 3.Similarly, extract the lib.rar and bin.rar files into 'WEB-INF/lib' and 'WEB-INF/bin' folders.
@rem =================================================================================================

set NLM=^


set NL=^^^%NLM%%NLM%^%NLM%%NLM%
echo %NL%%NL%%NL%%NL% 1. Download the dependencies from the links.%NL%%NL% 2. Extract the .jar files from the jars.rar into '/jars' folder. %NL%%NL% 3. Similarly, extract lib.rar and bin.rar into 'WEB-INF/lib' and 'WEB-INF/bin'.
echo.
echo %NL%%NL% ~~~~ Download .jar dependencies from the link after pressing enter. ~~~~
echo.
pause
start "" "https://yadi.sk/d/wpvQ38e-3Tm3do"
echo %NL%%NL% ~~~~ Download and extract bin.rar from the link into WEB-INF/bin. ~~~~
echo.
pause
start "" "https://yadi.sk/d/OyBEcwSe3Tm3oj"
echo %NL%%NL% ~~~~ Download and extract lib.rar from the link into WEB-INF/lib. ~~~~
echo.
pause
start "" "https://yadi.sk/d/xQHxZV_U3Tm3rm"